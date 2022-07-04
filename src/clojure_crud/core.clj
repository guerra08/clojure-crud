(ns clojure-crud.core
  (:require [clojure-crud.routes :as routes]
            [muuntaja.core :as mu]
            [reitit.coercion.malli :as ma]
            [reitit.ring :refer [ring-handler router]]
            [reitit.ring.coercion :refer [coerce-request-middleware
                                          coerce-response-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                                     format-request-middleware
                                                     format-response-middleware]]
            [ring.adapter.jetty :as j]))

(def app-router
  (router
    [[routes/food-routes]]
    {:data {:coercion   ma/coercion
            :muuntaja   mu/instance
            :middleware [format-negotiate-middleware
                         format-response-middleware
                         format-request-middleware
                         coerce-request-middleware
                         coerce-response-middleware]}}))

(def app
  (ring-handler app-router {:keywords? true :bigdecimals? true}))

(defn -main []
  (j/run-jetty app {:port 3000}))
