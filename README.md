# RPM plugin

A Leiningen plugin to create an RPM from build output. At the moment
it only works with Leiningen 2.

## Usage

Add `lein-rpm "0.0.1-SNAPSHOT"` to the plugins in `project.clj` and
then add an `:rpm` key to the project. This is a full example of all
the options that are supported at the moment.

    (defproject example "0.0.1-SNAPSHOT"
      :description "Project that needs an RPM"
      :plugins [[lein-rpm "0.0.1-SNAPSHOT"]]
      :rpm {:name "Name"
            :summary "RPM summary"
            :copyright "Andrew H Jones"
            :mappings [{:directory "/usr/local/bin/landfill"
                        :filemode 440
                        :username "dumper"
                        :groupname "dumpgroup"
                        :sources {:source ["target/classes"]}}]})

## License

Copyright (C) 2012 Andrew Jones

Distributed under the Eclipse Public License, the same as Clojure.
