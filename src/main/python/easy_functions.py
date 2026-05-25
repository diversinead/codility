# Q: Given an array of N integers, find the largest difference between 2 numbers
def find_max_difference(arr):
    if not arr or len(arr) < 2:
        return 0

    return max(arr) - min(arr)


# find longest sequence of zeros in binary representation of an integer
def longest_binary_gap(n):
    binary = bin(n)[2:]  # Get binary representation without '0b'
    gaps = binary.strip('0').split('1')  # Split by '1' and ignore trailing zeros
    return max((len(gap) for gap in gaps), default=0)

# Example
 # Output: 4





def main():
    print("\n*** PYTHON ***\n")

    numbers = [7, 2, 10, 1, 9]
    print("Max difference:", find_max_difference(numbers))

    print("longest binary gap: ", longest_binary_gap(529))

if __name__ == '__main__':
    main()
