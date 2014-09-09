(ns leiningen.rpm.middleware
  (:require [leiningen.rpm.build-info :refer [build-info]]))

(defn build-info->version
  [project]
  (assoc project :version (:version (build-info))))

(defn build-info->release
  [project]
  (assoc-in project [:rpm :release] (.substring (:revision (build-info)) 0 7)))
