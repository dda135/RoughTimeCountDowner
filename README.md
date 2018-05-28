# RoughTimeCountDowner
一个大概的倒计时器，其实就是基于Handler来操作，之所以说大概，是因为可能会因为一些系统或者耗时操作的原因，导致回调的比预期中要晚，不过一般来说是没有问题的
## 例子
启动一个倒计时器
```
    //总时长ms、步长ms
    timeCountDowner = new RoughTimeCountDowner(60000, 1000, new RoughTimeCountDowner.OnTickListener() {
            @Override
            public void onTick(long restTime, long initTime, long intervalTime) {
                textview.setText(restTime+"ms");
            }

            @Override
            public void onDone() {
                textview.setText("done");
            }
        });
    timeCountDowner.start();            
```
注意退出的时候关闭计时器，这是一个良好的习惯
```
    timeCountDowner.reset();
```
