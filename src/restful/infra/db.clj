(ns restful.infra.db
  (:require [mount.core :as mount]
            [monger.core :as mg]
            [restful.infra.config :as config]
            [restful.infra.log :as log])
  (:import [java.util.logging Logger Level]
           [org.bson Document]))

(defn- init-mongo-conn []
  (try
    (let [c (:conn (mg/connect-via-uri (config/mongo-conn-str)))]
      ; to reduce log noise
      (.setLevel (Logger/getLogger "org.mongodb.driver") Level/WARNING)
      c)
    (catch Exception e
      (log/fatal (str e)))))

(mount/defstate conn
                :start (init-mongo-conn)
                :stop (.close conn))

(defn- connect-mongo-db []
  (try
    (let [db (mg/get-db conn "restful")]
      ; to verify db server status
      (.command db "serverStatus")
      db)
    (catch Exception e
      (log/fatal (str e)))))

(mount/defstate db
                :start (connect-mongo-db))

