(ns nongrata.views.login
  (:use 
    noir.fetch.remotes
    [clojure.tools.logging :only [warn]]
    [noir.core :only [defpage]]
    [hiccup.core :only [html]])
  (:require
    [clj-http.client :as client]
    [nongrata.views.common :as common]
    [noir.content.getting-started]
    [noir.session :as session]))

(defn get-hostname []
  (.. java.net.InetAddress getLocalHost getHostName))

(defpage login "/login" []
         (common/layout
           [:p 
            (if (not (session/get "browser-id"))
              [:a#browserid-link {"href" "#" "title" "Sign-in with BrowserID"} 
               [:img#browserid {"src" "/img/sign_in_blue.png" "alt" "Sign in"}]]
              "You are signed in!")]))

(defremote apilogin [{:keys [assertion]}]
  (warn (str "recieved assertion: " assertion))
  (if-let [bid-response 
           (client/post "https://browserid.org/verify"
                        {:form-params {:assertion assertion :audience (get-hostname)}})]
    (do
      (warn (str "got response from browser id: " bid-response))
      true)))