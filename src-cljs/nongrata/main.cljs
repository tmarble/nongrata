(ns nongrata.main
  (:use [jayq.core :only [$]]
        [jayq.util :only [clj->js]])
  (:require [fetch.remotes :as remotes])
  (:require-macros [fetch.macros :as fm]))

(defn log [& args]
  (.log js/console (apply pr-str args)))

(defn log-obj [obj]
  (.log js/console obj))

(defn gotAssertion
  [assertion]
  (js/alert (str "recieved encrypted response from \nclient after asking browserid.org\n(do not show this in a production env)\n|" assertion "|"))
  (if assertion
    (fm/remote (apilogin assertion) [response]
               (do
                 (log "response:" response)
                 (if (and 
                       (not (or (empty? response) (= "" response) (nil? response)))
                       (= "okay" (:status response)))
                   (do
                     (log "authentication success!")
                     (.reload (.-location js/window)))
                   (js/alert (str "failed authentication:\n" (:reason response))))))
    (js/alert "browserid.org gave us a nil response back...")))

; TODO make this more jayq, less interopy
(.bind ($ "#browserid") "click" (fn[evt] (js/alert (str "button clicked! event: " evt))
                                  (do                       
                                    (navigator.id/get gotAssertion)  
                                    false)))  

(js/alert "JS evaluation reached bottom of the main.js file...")

