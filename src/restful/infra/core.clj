(ns restful.infra.core
  (:require [restful.infra.log]
            [mount.core :as mount]))

(defn start-app []
  (mount/start))

(defn stop-app []
  (mount/stop))