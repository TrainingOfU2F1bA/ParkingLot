# 1. ParkingLot 

## 停车

Given: 一辆车，有车牌，且车牌在停车场不存在, 且车位充足

When: 停车

Then: 停车成功，吐出一张停车小票，小票上是被停车的车牌号

---

Given: 一辆车，有车牌，且车牌在停车场存在, 且车位充足

When: 停车

Then: 停车失败

---


Given: 一辆车，无车牌

When: 停车

Then: 停车失败

---


Given: 没车

When: 停车

Then: 停车失败

---

Given: 一辆车，有车牌，且车牌在停车场不存在, 且车位不足

When: 停车

Then: 停车失败

---


## 取车

Given: 带有车牌号的停车小票，停车场有对应的车

When: 取车

Then: 取车成功

---


Given: 无车牌号的停车小票

When: 取车

Then: 取车失败

---

Given: 无小票

When: 取车

Then: 取车失败

---


Given: 带有车牌号的停车小票， 停车场没有对应车牌的车

When: 取车

Then: 取车成功

---

#2. Graduate Parking Boy

**Context: 有A和B两个停车场，A的次序优先于B**

## 停车
Given: 一辆车，有车牌，且车牌在A,B停车场都不存在, 且A的车位充足

When: 停车

Then: 停车成功，停在A停车场，吐出一张停车小票，小票上是被停车的车牌号

---

Given: 一辆车，有车牌，且车牌在A,B停车场都不存在, A停满了，但B的车位充足

When: 停车

Then: 停车成功，停在B停车场，吐出一张停车小票，小票上是被停车的车牌号

---

Given: 一辆车，有车牌，且车牌在A,B停车场都不存在, 且A，B车位都停满了

When: 停车

Then: 停车失败

---

Given: 一辆车，无车牌

When: 停车

Then: 停车失败

---


Given: 没车

When: 停车

Then: 停车失败

---

Given: 一辆车，有车牌，且车牌在停车场不存在, 且车位不足

When: 停车

Then: 停车失败


## 取车

Given: 带有车牌号的停车小票，A停车场有对应的车

When: 取车

Then: 取车成功

---

Given: 带有车牌号的停车小票，B停车场有对应的车

When: 取车

Then: 取车成功

---


Given: 无车牌号的停车小票

When: 取车

Then: 取车失败

---

Given: 无小票

When: 取车

Then: 取车失败

---


Given: 带有车牌号的停车小票， A和B停车场没有对应车牌的车

When: 取车

Then: 取车成功
