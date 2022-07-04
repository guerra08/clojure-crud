(defproject clojure-crud "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [metosin/reitit "0.5.18"]
                 [metosin/malli "0.8.9"]
                 [com.github.seancorfield/next.jdbc "1.2.780"]
                 [com.h2database/h2 "2.1.214"]]
  :repl-options {:init-ns clojure-crud.core})
