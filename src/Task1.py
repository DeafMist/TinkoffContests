dep1 = list(map(int, input().split()))
dep2 = list(map(int, input().split()))

res = max(abs(dep1[0] - dep2[2]), abs(dep1[1] - dep2[3]), abs(dep2[0] - dep1[2]), abs(dep2[1] - dep1[3])) ** 2
print(res)

