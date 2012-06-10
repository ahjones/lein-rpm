(ns leiningen.rpm
  (:require [clojure.java.data :as data])
  (:import [org.codehaus.mojo.rpm RPMMojo AbstractRPMMojo Mapping Source SoftlinkSource]
           [org.apache.maven.project MavenProject]
           [org.apache.maven.shared.filtering DefaultMavenFileFilter]
           [org.codehaus.plexus.logging.console ConsoleLogger]))

(defn create-array-list [clj-list]
  (let [list (java.util.ArrayList.)]
    (doseq [item clj-list] (.add list item))
    list))

(defn create-source [class [source & rest]]
  (if source (cons (data/to-java class source) (create-source class rest)) ()))

(defn create-sources [{:keys [source softlinkSource]}]
  (concat (create-source Source source) (create-source SoftlinkSource softlinkSource)))

(defn create-mapping [{s :sources :as mapping}]
  (data/to-java Mapping (assoc mapping :sources (create-sources s))))

(defn create-mappings [[mapping & rest]]
  (if mapping (cons (create-mapping mapping) (create-mappings rest)) ()))

(defn set-mojo! [object name value]
  (let [field (.getDeclaredField AbstractRPMMojo name)]
    (.setAccessible field true)
    (.set field object value))
  object)

(defn createBaseMojo []
  (let [mojo (RPMMojo.)
        fileFilter (DefaultMavenFileFilter.)]
    (set-mojo! mojo "project" (MavenProject.))
    (.enableLogging fileFilter (ConsoleLogger. 0 "Logger"))
    (set-mojo! mojo "mavenFileFilter" fileFilter)))

(defn createScriptlet [description]
  (data/to-java Scriptlet ))

(defn rpm
  "Create an RPM"
  [{{:keys [summary name copyright mappings preinstall postinstall preremove postremove]} :rpm :keys [version]} & keys]
  (let [mojo (createBaseMojo)]
    (set-mojo! mojo "projversion" version)
    (set-mojo! mojo "name" name)
    (set-mojo! mojo "summary" summary)
    (set-mojo! mojo "copyright" copyright)
    
    (set-mojo! mojo "mappings" (create-array-list (create-mappings mappings)))
    (set-mojo! mojo "preinstallScriptlet" (data/to-java preinstall))
    (set-mojo! mojo "postinstallScriptlet" (data/to-java postinstall))
    (set-mojo! mojo "preremoveScriptlet" (data/to-java preremove))
    (set-mojo! mojo "postremoveScriptlet" (data/to-java postremove))
    (.execute mojo)))
