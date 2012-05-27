;; Example project

(defproject lein-rpm "0.1-SNAPSHOT"
  :description "Create an RPM"
  :dependencies [[org.codehaus.mojo/rpm-maven-plugin "2.1-alpha-1"]]
  :eval-in-leiningen true
  :plugins [[lein-pprint "1.1.1"]
            [lein-rpm "0.0.2-SNAPSHOT"]]
  :rpm {:name "Name"
        :summary "RPM summary"
        :copyright "Andrew H Jones"
        :mappings [{:directory "/usr/local/bin/landfill"
                    :filemode "440"
                    :username "dumper"
                    :groupname "dumpgroup"
                    :sources {:source [{:location "target/classes"}
                                       {:location "src"}]
                              :softlinkSource [{:location "/usr/local/bin/new.sh"
                                                :destination "old.sh"}]}}]})