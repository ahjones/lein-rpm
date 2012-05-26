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

(defn arrayList [cljList]
  (let [list (java.util.ArrayList.)]
    (doseq [thing cljList] (.add list thing))
    list))

(defn create-sources [{source :source}]
  (for [path source] (doto (Source.) (.setLocation path))))

(defn create-mapping [{:keys [directory filemode username groupname sources]}]
  (doto (Mapping.)
    (.setDirectory directory)
    (.setFilemode filemode)
    (.setUsername username)
    (.setGroupname groupname)
    (.setSources (arrayList (create-sources sources)))))

(defn create-mappings [[mapping & rest]]
  (if mapping (cons (create-mapping mapping) (create-mappings rest)) ()))

(defn createBaseMojo []
  (let [mojo (RPMMojo.)
        fileFilter (DefaultMavenFileFilter.)]
    (set-mojo! mojo "project" (MavenProject.))
    (.enableLogging fileFilter (ConsoleLogger. 0 "Logger"))
    (set-mojo! mojo "mavenFileFilter" fileFilter)))

(defn rpm
  "Create an RPM"
  [{{:keys [summary name copyright mappings]} :rpm :keys [version]} & keys]

  (let [mojo (createBaseMojo)]
    (set-mojo! mojo "projversion" version)
    (set-mojo! mojo "name" name)
    (set-mojo! mojo "summary" summary)
    (set-mojo! mojo "mappings" (arrayList (create-mappings mappings)))
    (set-mojo! mojo "copyright" copyright)
    (.execute mojo)))
