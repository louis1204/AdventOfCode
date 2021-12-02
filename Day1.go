package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
)

func DoDay1() {
	file, err := os.Open("input1.txt")
	if err != nil {
		log.Fatal("Failed to open file")
	}
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)

	var prev int = -1
	res := 0
	var list = make([]int, 0)
	for scanner.Scan() {
		var next int
		temp, err := strconv.ParseInt(scanner.Text(), 10, 0)
		next = int(temp)

		if err != nil {
			log.Fatal("Error parsing string")
		}
		if prev != -1 && next > prev {
			res++
		}
		list = append(list, next)
		prev = next
	}
	fmt.Printf("res part 1: %d\n", res)

	prev = -1
	res = 0
	for i := 0; i < len(list)-2; i++ {
		sum := list[i] + list[i+1] + list[i+2]
		if prev != -1 && sum > prev {
			res++
		}
		prev = sum
	}
	fmt.Printf("res part 2: %d\n", res)
}
