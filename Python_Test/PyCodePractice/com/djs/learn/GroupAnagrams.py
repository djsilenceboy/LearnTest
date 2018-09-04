'''
@author: Du Jiang
https://leetcode.com/problems/group-anagrams/description/
'''


class Solution:
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        intermediate = {}
        for str in strs:
            key = "".join(sorted(list(str)))
            if key in intermediate.keys():
                values = intermediate.get(key)
                values.append(str)
            else:
                intermediate[key] = [str]

        return [item for item in intermediate.values()]

    def groupAnagrams_2(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        intermediate = {}
        for str in strs:
            key = "".join(sorted(list(str)))
            if key in intermediate.keys():
                intermediate[key].append(str)
            else:
                intermediate[key] = [str]

        return list(intermediate.values())


def test(strs):
    solution = Solution()
    result = solution.groupAnagrams(strs)
    print("result =", result)
    print("-" * 80)


def test_2(strs):
    solution = Solution()
    result = solution.groupAnagrams_2(strs)
    print("result =", result)
    print("-" * 80)


def main():
    # [["ate","eat","tea"], ["nat","tan"], ["bat"]]
    strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    test(strs)

    test_2(strs)


if __name__ == '__main__':
    main()
