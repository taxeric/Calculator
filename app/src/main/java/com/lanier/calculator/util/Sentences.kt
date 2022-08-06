package com.lanier.calculator.util

import com.lanier.calculator.entity.Sentences
import kotlin.random.Random

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2022/8/5 10:16
 * Desc  :
 */
val sentences = listOf(
    Sentences("洛克王国", "信步流年，付印岁月"),
    Sentences("异度之刃", "并不需要什么永远，就算只是刹那间，只要能彼此相依就已足够—"),
    Sentences("《可塑性记忆》", "时光流转，愿你与真爱之人能够重逢"),
    Sentences("洛克王国", "学到很多东西的决窍，就是不要一下子学很多的东西"),
    Sentences("艾跃进教授", "常回家做饭，分担家务。报喜不报忧，体贴父母。在外人面前尊重父母，爱父母配偶之所爱。"),
    Sentences("洛克王国", "有时候，想象力可以无所不能！只有想不到，没有做不到！"),
    Sentences("《游山西村》","山重水复疑无路，柳暗花明又一村"),
    Sentences("奥拉星", "我只愿花未谢，雨未消，你未离去"),
    Sentences("《超兽武装》", "生命的目的，并不是为了存在，而是为了燃烧。燃烧才会有光亮，哪怕只有一瞬的光亮也好"),
    Sentences("《海贼王》","人的梦想啊，是永远不会结束的"),
    Sentences("《秒速5厘米》","我要用什么样的速度，才能与你相遇"),
    Sentences("旷野之息","你可别像我一样阿，要活得精彩，让人生无悔"),
    Sentences("星际战甲","梦…不是关于你是什么，而是关于你想成为什么"),
    Sentences("《海贼王》", "人究竟什么时候会死，是心脏被枪打中的时候?不对。得到不治之症吗?也不对。喝了剧毒蘑菇汤之后吗?当然不是。而是被世人遗忘的时候"),
    Sentences("赛尔号","这个世界有了朋友才不会孤单"),
    Sentences("英雄联盟", "一曲终了，繁花散尽，伊人已逝，只余一声空叹"),
    Sentences("《仙剑奇侠传》", "人生漂泊无依，有如浮萍菱花，随水飘流"),
    Sentences("《迪迦奥特曼》", "要实现梦想，就要向现实挑战!"),
    Sentences("奥拉星", "无论什么事，到了明天，总会有办法的，今天就为明天的事担心，岂非划不来"),
    Sentences("《雪中忆李楫》", "冰雪满阡陌，故人不可期"),
    Sentences("洛克王国", "梦想无论怎样模糊，却依然潜伏在我们心底"),
    Sentences("《绝对双刃》", "凝结羁绊之人，尽可能地共享时光吧。喜悦之时，悲伤之刻，健康之辰，直到死亡分离两人之日为止。"),
    Sentences("艾跃进教授", "大炮是用来丈量国土面积的。尊严只在剑锋之上，真理只在大炮射程之内"),
    Sentences("《超兽武装》", "我们最大的敌人，其实就是我们自己"),
    Sentences("英雄联盟", "你的遗体将慢慢消散，化为永恒，就像沙漠中的沙砾那样"),
    Sentences("《为美好的世界献上祝福!》", "和你们一起冒险非常愉快。那是在我至今为止的人生中，最快乐的一段时间。想必我今后是绝对忘不掉和你们一起冒险的日子的吧"),
    Sentences("《神奇宝贝》", "当一个生命与另一个生命相遇的时候，会孕育出什么呢"),
    Sentences("英雄联盟", "我一直急速前行，穿梭于人人之间，试图叫应接不暇的风景让我褪去对你的思念"),
    Sentences("怪物猎人", "愿指引明路的苍蓝星为你们闪烁"),
    Sentences("《加勒比海盗》", "我们的命运纠缠在一起，但无缘结连理"),
    Sentences("《星际穿越》", "墨菲定律并非指的是那些变坏的事情必会发生…...而是指那些能够发生的事情，就会发生。"),
    Sentences("奥拉星", "我曾跨山涉水，见山是你，见水也是你"),
    Sentences("机甲旋风", "也许，我们永远找不到伊西，也许，故事的结局并不完美。但是，请永远记得我们曾经的努力和孤注一掷的勇气"),
)

val defaultSentence = sentences[(Math.random() * sentences.size).toInt()]
