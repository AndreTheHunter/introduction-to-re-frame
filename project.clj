(defproject com.github.andrethehunter/introduction-to-re-frame "1.0.0"
  :dependencies [[thheller/shadow-cljs "2.8.35"]
                 [re-frame "0.10.6"]
                 ;keyboard
                 [re-pressed "0.3.0"]
                 ;JS interop
                 [binaryage/oops "0.7.0"]
                 ;live recompiling (for examples)
                 [org.clojure/tools.reader "1.3.2"]]
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.10"]]}})
