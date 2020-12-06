(ns restful.infra.config
  (:require [restful.infra.log :as log]
            [mount.core :as mount]))

(defn- read-env-var
  ([name]
   (read-env-var name nil))
  ([name default]
    (if-let [value (System/getenv name)]
      value
      (or default (throw (ex-info (format "Environment variable '%s' not found" name) {}))))))

(defn- read-config []
  (try
    {:mongo-conn-str (read-env-var "MONGO_CONN_STRING")
     :api-port (read-env-var "API_PORT" 8080)}
    (catch Exception e
      (log/fatal (str e)))))

(mount/defstate root
                :start (read-config))

(defn mongo-conn-str []
  (:mongo-conn-str root))

(defn api-port []
  (:api-port root))