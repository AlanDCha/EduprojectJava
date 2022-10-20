### Algorithm design and analysis

The present code is an algorithm that detects your number formed by 4 digits in less than 10 attempts.
I found 5 cases, which are:
1. **Case 1**: The number is formed by 1 digit repeated 4 times.
Example: `3333`
2. **Case 2**: The number is formed by 1 digit repeated 3 times and other digit.
Example: `4544`
3. **Case 3**: The number is formed by 2 digit repeated 2 times.
Example: `8787`
4. **Case 4**: The number is formed by 1 digit repeated 2 times and different random digits.
Example: `1341`
5. **Case 5**: The number is formed by 4 different random digits.
Example: `1024`

This algorithm works the following way. It compares the number to find with the generated number and verify how many digits are in correct position and wrong position. For example:

|Numbers to find|Number to compare| Correct position | Wrong position |
|     :----:    |:----:           |      :----:      |     :----:     |
|3034           |3374             |2                 |1               |

That's because:

| Index 0 | Index 1 | Index 2 | Index 3 |
|  :---:  |  :---:  |  :---:  |  :---:  |
|    3    |    0    |    3    |    4    |
|    3    |    3    |    7    |    4    |

Both indices 0 and 3 have the same number **3, 4**. Therefore it is written 2 in `correct position`. The number 3 also is located in different position that's why it is written 1 in `wrong position`.

The app has 2 ways, which are:
- You find the number
- Algorithm finds the number. But in this way you have 2 options:
    - You decide the number to find
    - The system itself decides the number to find

Are you capable to find the number in less than 10 attempts? Good luck :+1: