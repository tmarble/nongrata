(ns nongrata.main
  (:use [jayq.core :only [$]]
        [jayq.util :only [clj->js]]))

(defn log [& args]
  (.log js/console (apply pr-str args)))

(defn log-obj [obj]
  (.log js/console obj))

(defn gotAssertion
  [assertion]
  (js/alert (str "callback invoked: " assertion)))

(.bind ($ "#browserid") "click" (fn[evt] (js/alert (str "button clicked! event: " evt))
                                  (do                       
                                    navigator.id/get(gotAssertion)  
                                    false)))  
                                  
(js/alert "JS evaluation reached bottom of the main.js file...")

