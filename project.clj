(defproject nongrata "0.1.0-SNAPSHOT"
  :description "Mozilla Persona (BrowserID) example using Noir"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [javax.mail/mail "1.4.2"]
                 [noir "1.3.0-beta3"]]
  :dev-dependencies [[lein-eclipse "1.0.0"]]
  :plugins [[lein-cljsbuild "0.1.8"]]
  :cljsbuild {
     :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-path "src-cljs"
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
          :output-to "war/javascripts/main.js"  ; default: main.js in current directory
          :optimizations :whitespace
          :pretty-print true}}]}
  :main nongrata.server ) ;; please keep the space after the class name
