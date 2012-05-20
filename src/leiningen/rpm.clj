(ns leiningen.rpm
    (:import [org.codehaus.mojo.rpm RPMMojo AbstractRPMMojo Mapping Source]
           [org.apache.maven.project MavenProject]
           [org.apache.maven.shared.filtering DefaultMavenFileFilter]
           [org.codehaus.plexus.logging.console ConsoleLogger]))

(defn set-mojo! [object name value]
  (let [field (.getDeclaredField AbstractRPMMojo name)]
    (.setAccessible field true)
    (.set field object value))
  object)

(defn rpm
  "Create an RPM"
  [project & keys]

  (let [mojo (RPMMojo.)
        fileFilter (DefaultMavenFileFilter.)
        mapping (Mapping.)
        source (Source.)]
    (set-mojo! mojo "projversion" "1.0.0-SNAPSHOT")
    (set-mojo! mojo "name" "aj_rpm")
    (.setLocation source "classes")
    (.setDirectory mapping "/tmp")
    (.setSources mapping (doto (java.util.ArrayList.) (.add source)))
    (set-mojo! mojo "mappings" (doto (java.util.ArrayList.) (.add mapping)))
    (set-mojo! mojo "project" (MavenProject.))
    (.enableLogging fileFilter (ConsoleLogger. 0 "Logger"))
    (set-mojo! mojo "mavenFileFilter" fileFilter)
    (set-mojo! mojo "summary" "summary")
    (set-mojo! mojo "copyright" "copyright")
    (.execute mojo)))
