(defproject lein-rpm "0.0.6-SNAPSHOT"
  :description "Create an RPM"
  :dependencies [[org.codehaus.mojo/rpm-maven-plugin "2.1-alpha-1"]
                 [clj-yaml "0.4.1-SNAPSHOT"]
                 [org.clojure/java.data "0.1.1"]]
  :eval-in-leiningen true
  :plugins [[lein-pprint "1.1.1"]]

  :repositories [["primedia" {:url "http://nexus.idg.primedia.com/nexus/content/repositories/primedia"
                              :sign-releases false}]
                 ["snapshots" {:url "http://nexus.idg.primedia.com/nexus/content/repositories/snapshots"
                               :sign-releases false}]])
