(ns nongrata.main
  (:use [jayq.core :only [$]]
        [jayq.util :only [clj->js]]))

(defn log [& args]
  (.log js/console (apply pr-str args)))

(defn log-obj [obj]
  (.log js/console obj))

; Using jayq, do this with jquery...
; $("#signin-img").bind('click', function() { alert("button clicked!"); });
(.bind ($ "#signin-img") "click" (fn[] (js/alert "button clicked!")))

; If we got here, everything should be good
(js/alert "JS Code compiled and running 5...")

