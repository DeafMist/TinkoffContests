n, L = map(int, input().split())

coords = list(map(int, input().split()))
prev = coords[0]
count = 1
for i in range(1, n):
  cur = coords[i]
  if cur - prev > L:
    count += 1
    prev = cur
print(count)