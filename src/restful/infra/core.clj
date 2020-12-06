(ns restful.infra.core
  (:require [restful.infra.log]
            [restful.infra.db]
            [restful.infra.server]
            [mount.core :as mount]))

(defn start-app []
  (mount/start))

(defn stop-app []
  (mount/stop))