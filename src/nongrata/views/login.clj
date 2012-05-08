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
              [:a#browserid-link {"href" "#" "title" "Sign-in with BrowserID"} 
               [:img#browserid {"src" "/img/sign_in_blue.png" "alt" "Sign in"}]]
              "You are signed in!")]))

; Successful response from Browser ID should look something like so:
;{:status 200 
; :headers 
; {"content-type" "application/json; charset=utf-8", 
;  "strict-transport-security" "max-age=2419200", 
;  "date" "Tue, 08 May 2012 01:19:35 GMT", 
;  "connection" "close", 
;  "content-length" "121"}, 
; :body 
; "{\"status\":\"okay\",\"email\":\"foo@bar.com\",\"audience\":\"localhost\",\"expires\":1336440085619,\"issuer\":\"browserid.org\"}"}

(defremote apilogin [assertion]
  (info (str "recieved assertion: " assertion "\n\nNow submitting back to browserid.org"))
  (info (str "posting audience '" (get-hostname) "'.\nBe aware that if you're surfing the test site at a different URL, this will fail."))
  (if-let [bid-response 
           (client/post "https://browserid.org/verify"
                        {:form-params {:assertion assertion :audience "localhost"}})]
    (do
      (info (str "got response from browser id: " bid-response))
      (let [body-map (read-json (:body bid-response))]
        (if (= 200 (:status bid-response))
          (do
            (info (str "HTTP 200 OK from browserid.org: " body-map))
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