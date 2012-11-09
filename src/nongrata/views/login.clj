(ns nongrata.views.login
  (:use 
    noir.fetch.remotes
    [clojure.data.json :only [read-json json-str]]
    [clojure.tools.logging :only [error info]]
    [noir.core :only [defpage]]
    [hiccup.core :only [html]])
  (:require
    [clj-http.client :as client]
    [nongrata.views.common :as common]
    [noir.content.getting-started]
    [noir.session :as session]))

(defn get-hostname 
  "Returns the hostname of this machine. May work in some circumstances if it's what the public IP address maps to."
  []
  (.. java.net.InetAddress getLocalHost getHostName))

(defpage login "/login" []
  (common/layout
    [:p 
     (if (not (session/get "browser-id"))
       [:div [:p "You aren't logged in. Login via Persona."]
        [:a#browserid-link {"href" "#" "title" "Sign-in with BrowserID" "class" "persona-button"} 
         [:span "Sign in with your email"]]
        [:a#browserid-logout-link {"href" "#" "title" "Log out" "class" "persona-button noshow"} 
         [:span "Log out"]]]
       [:div [:p "You are signed in!"]
        [:a#browserid-link {"href" "#" "title" "Sign-in with BrowserID" "class" "persona-button noshow"} 
         [:span "Sign in with your email"]]
        [:a#browserid-logout-link {"href" "#" "title" "Log out" "class" "persona-button"} 
         [:span "Log out"]]])]))

; Successful response from Browser ID should look something like so:
;{:status 200 
; :headers 
; {"content-type" "application/json; charset=utf-8", 
;  "strict-transport-security" "max-age=2419200", 
;  "date" "Tue, 08 May 2012 01:19:35 GMT", 
;  "connection" "close", 
;  "content-length" "121"}, 
; :body 
; "{\"status\":\"okay\",\"email\":\"foo@bar.com\",\"audience\":\"localhost\",\"expires\":1336440085619,\"issuer\":\"login.persona.org\"}"}

(defremote apilogin [assertion]
  (info (str "recieved assertion: " assertion "\n\nNow submitting back to login.persona.org"))
  (info (str "posting audience '" (get-hostname) "'.\nBe aware that if you're surfing the test site at a different URL, this will fail."))
  (if-let [bid-response 
           (client/post "https://verifier.login.persona.org/verify"
                        {:form-params {:assertion assertion :audience "localhost"}})]
    (do
      (info (str "got response from browser id: " bid-response))
      (let [body-map (read-json (:body bid-response))]
        (if (= 200 (:status bid-response))
          (do
            (info (str "HTTP 200 OK from login.persona.org: " body-map))
            (if (= "okay" (:status body-map))
              (do 
                (info "Status: okay, creating authenticated user session...")
                (session/put! "browser-id" body-map)))
            body-map)
          (do 
            (error (str "http response:" (:status bid-response)))
            body-map))))
    (do
      (error "document contains no response")
      {:status "error no data"})))

(defremote logout []
  (info "received logout request")
  (session/put! "browser-id" nil))