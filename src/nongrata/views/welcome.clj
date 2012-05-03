(ns nongrata.views.welcome
  (:require [nongrata.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to nongrata"]))

;(defpage "/cljs" []
;  (common/layout
;  [:div#header
;   [:h2.pagetitle "Twitter Searcher"]]
;     [:div#inputbar
;       [:input#tag {:type "text"}]]
;       [:div#tweetsbox
;         [:div#tweets]]))
;
;(defremote load-tweets [hashtag]
;  (when-let [tweets (tw/search hashtag)] ;do search request
;    (let [{:keys [results]} tweets] ;get the results (tweets) from the response
;      (map simplify-tweet results))))
;
