(ns user
  (:require [restful.infra.core :as infra]
            [clojure.tools.namespace.repl :as repl]))

(defn reset []
  (infra/stop-app)
  (repl/refresh :after 'infra/start-app))

(defn stop []
  (infra/stop-app))

(comment
  (reset)
  (stop))