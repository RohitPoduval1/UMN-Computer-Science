# Warmups for Lab 1

# Milestone 2 for Lab 1
def most_common_char(s):
    char_dict = {}
    for char in s:
        if char not in char_dict:
            char_dict[char] = 1
        else:
            char_dict[char] += 1
    current_max = 0
    most_frequent_char = ""
    for key in char_dict.keys():
        if char_dict[key] > current_max:
            current_max = char_dict[key]
            most_frequent_char = key
    return [most_frequent_char, current_max]

# Milestone 3 for Lab 1
def is_palindrome(s):
    if len(s) == 1:
        return True
    elif s[0] == s[-1]:
        return is_palindrome(s[1:-1])
    else:
        return False


def main():
    print(most_common_char("bejeweled"))    # e, 4
    print(is_palindrome("racecar"))         # True
    print(is_palindrome("old"))             # False
    print(is_palindrome("radar"))           # True
    print(is_palindrome("Hi"))              # False

    


if __name__ == "__main__":
    main()
