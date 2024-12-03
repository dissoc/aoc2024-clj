(ns aoc2024-clj.day3.code)

(def input-data (slurp "resources/day3/data"))

(defn part-1 []
  (loop [muls (re-seq #"mul\(\d{1,3},\d{1,3}\)" input-data)
         sum  0]
    (let [[a b]   (clojure.string/split (first muls) #",")
          product (* (-> a
                         (clojure.string/replace "mul(" "")
                         Integer.
                         )
                     (-> b
                         (clojure.string/replace ")" "")
                         Integer.))
          sum     (+ sum product)]
      (if (next muls)
        (recur (rest muls) sum)
        sum))))
