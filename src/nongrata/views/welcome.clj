(ns nongrata.views.welcome
  (:require [nongrata.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

;<a href="#" id="browserid" title="Sign-in with BrowserID">
;  <img src="/images/sign_in_blue.png" alt="Sign in">
;</a>
(defpage "/welcome" []
         (common/layout
           [:p "Welcome to nongrata"]
           [:a {:href "#" :id "browserid" :title "Sign-in with BrowserID"} "Login"]))
