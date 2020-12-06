(ns restful.infra.config
  (:require [restful.infra.log :as log]
            [mount.core :as mount]))

(defn- read-env-var [name]
  (if-let [value (System/getenv name)]
    value
    (throw (ex-info (format "Environment variable '%s' not found" name) {}))))

(defn- read-config []
  (try
    {:mongo-conn-str (read-env-var "MONGO_CONN_STRING")}
    (catch Exception e
      (log/fatal (str e)))))

(mount/defstate root
                :start (read-config))

(defn mongo-conn-str []
  (:mongo-conn-str root))