# 1. ParkingLot 

## 停车

Given: 一辆车，有车牌，且车牌在停车场不存在

When: 停车

Then: 停车成功，吐出一张停车小票，小票上是被停车的车牌号

---

Given: 一辆车，有车牌，且车牌在停车场存在

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

