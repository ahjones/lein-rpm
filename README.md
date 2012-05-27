# RPM plugin

A Leiningen plugin to create an RPM from build output. At the moment
it only works with Leiningen 2.

## Usage

Add `lein-rpm "0.0.1-SNAPSHOT"` to the plugins in `project.clj` and
then add an `:rpm` key to the project. This example shows most of the
options that are available.

    (defproject lein-rpm "0.1-SNAPSHOT"
      :description "Create an RPM"
      :dependencies [[org.codehaus.mojo/rpm-maven-plugin "2.1-alpha-1"]]
      :eval-in-leiningen true
      :plugins [[lein-pprint "1.1.1"]
                [lein-rpm "0.0.2"]]
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

## License

Copyright (C) 2012 Andrew Jones

Distributed under the Eclipse Public License, the same as Clojure.
