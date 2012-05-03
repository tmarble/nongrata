(defproject nongrata "0.1.0-SNAPSHOT"
  :description "Mozilla Persona (BrowserID) example using Noir"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [javax.mail/mail "1.4.2"]
                 [noir "1.3.0-beta3"]
                 [clj-http "0.2.6"]
                 [cheshire "2.2.0"]
                 [jayq "0.1.0-SNAPSHOT"]
                 [crate "0.1.0-SNAPSHOT"]
                 [fetch "0.1.0-SNAPSHOT"]]
  :dev-dependencies [[lein-eclipse "1.0.0"]]
  :plugins [[lein-cljsbuild "0.1.8"]]
  :cljsbuild {
              :builds [{
                        ; The path to the top-level ClojureScript source directory:
                        :source-path "src-cljs"
                        ; The standard ClojureScript compiler options:
                        ; (See the ClojureScript compiler documentation for details.)
                        :compiler {
                                   :output-to "resources/public/js/main.js"  ; default: main.js in current directory
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :jvm-opts ["-server"] 
  :main nongrata.server)
