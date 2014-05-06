(ns leiningen.rpm.build-info
  (:require [clj-yaml.core :as yaml]))

(def ^:private +build-info-file+ "./BUILD_INFO")

(defn build-info
  ([]
     (build-info +build-info-file+))
  ([filename]
     (yaml/parse-string (slurp filename))))

;; ./build_info.yml 
;; ---
;; revision: 1fec6781fe2de97fe6be92edec7065d19bd61ac5
;; version: '2014_04_18_15_04'
;; build_time: 2014-04-18 15:04:43.588829000 -04:00
