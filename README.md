# RPM plugin

A Leiningen plugin to create an RPM from build output. At the moment
it only works with Leiningen 2.

## Usage

Add `lein-rpm "0.0.3"` to the plugins in `project.clj` and
then add an `:rpm` key to the project. This example shows most of the
options that are available.

    (defproject example "0.1-SNAPSHOT"
      :description "Create an RPM"
      :plugins [[lein-rpm "0.0.5"]]
      :rpm {:name "Name"
            :summary "RPM summary"
            :copyright "Andrew H Jones"
            :workarea "target"
            :mappings [{:directory "/usr/local/bin/landfill"
                        :filemode "440"
                        :username "dumper"
                        :groupname "dumpgroup"
                        :preinstall {:scriptfile "script.sh"}
                        :requires ["trash-truck > 1.0"]
                        ;; There are also postinstall, preremove and postremove
                        :sources {:source [{:location "target/classes"}
                                           {:location "src"}]
                                  :softlinkSource [{:location "/usr/local/bin/new.sh"
                                                    :destination "old.sh"}]}}]})

## License

Copyright (C) 2012 Andrew Jones

Distributed under the Eclipse Public License, the same as Clojure.
