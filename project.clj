(defproject lein-rpm "0.0.7-SNAPSHOT"
  :description "Create an RPM"
  :dependencies [[org.codehaus.mojo/rpm-maven-plugin "2.1.5"]
                 [org.clojure/java.data "0.1.1"]]
  :eval-in-leiningen true
  :plugins [[lein-pprint "1.1.1"]])
