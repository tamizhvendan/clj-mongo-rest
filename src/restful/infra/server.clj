(ns restful.infra.server
  (:require [restful.app :refer [app]]
            [ring.adapter.jetty :as jetty]
            [restful.infra.config :as config]
            [mount.core :as mount]
            [cheshire.generate :as chesire]
            [restful.infra.log :as log]))


(chesire/add-encoder
  org.bson.types.ObjectId
  (fn [c json-generator]
    (.writeString json-generator (str c))))

(defn start-server []
  (try
    (jetty/run-jetty (app) {:port  (config/api-port)
                            :join? false})
    (catch Exception e
      (log/fatal (str e)))))

(mount/defstate server
                :start (start-server)
                :stop (.stop server))