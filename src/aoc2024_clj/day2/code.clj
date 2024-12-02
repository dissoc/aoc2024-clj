(ns aoc2024-clj.day2.code)

(def input-data (slurp "resources/day2/data"))

(def parsed-data (->> input-data
                      clojure.string/split-lines
                      (map (fn [line]
                             (map #(Integer. %)
                                  (clojure.string/split line #" "))))))

(defn part-1 []
  (loop [counter 0
         reports parsed-data]
    (let [report  (first reports)
          safe?   (every?
                   (fn [[a b c]]
                     (let [ab-diff (- b a)
                           bc-diff (- c b)]
                       (and (<= 1 (abs ab-diff) 3)
                            (<= 1 (abs bc-diff) 3)
                            (= (pos? ab-diff) (pos? bc-diff)))))
                   (partition 3 1 report))
          counter (if safe?
                    (inc counter)
                    counter)]
      (if (next reports)
        (recur counter (rest reports))
        counter))))
