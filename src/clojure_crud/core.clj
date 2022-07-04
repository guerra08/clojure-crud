(ns clojure-crud.core
  (:require [clojure-crud.routes :as routes]
            [muuntaja.core :as mu]
            [org.httpkit.server :as h]
            [reitit.coercion.malli :as ma]
            [reitit.ring :refer [ring-handler router]]
            [reitit.ring.coercion :refer [coerce-exceptions-middleware
                                          coerce-request-middleware
                                          coerce-response-middleware]]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                                     format-request-middleware
                                                     format-response-middleware]]))

(def app-router
  (router
    [["/api/v1"
      [routes/food-routes]
      ]]
    {:data {:coercion   ma/coercion
            :muuntaja   mu/instance
            :middleware [format-negotiate-middleware
                         format-response-middleware
                         format-request-middleware
                         exception-middleware
                         coerce-request-middleware
                         coerce-response-middleware
                         coerce-exceptions-middleware]}}))

(def app
  (ring-handler app-router {:keywords? true :bigdecimals? true}))

(defonce app-instance (atom nil))

(defn start-server []
  (reset! app-instance (h/run-server app {:port 3000 :join? false})))

(defn stop-server []
  (when @app-instance
    (@app-instance :timeout 100))
  (reset! app-instance nil))

(defn restart-server []
  (stop-server)
  (start-server))

(defn -main []
  (h/run-server app {:port 3000}))
