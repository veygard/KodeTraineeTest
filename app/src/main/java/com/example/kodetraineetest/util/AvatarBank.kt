package com.example.kodetraineetest.util

object AvatarBank {

    fun getAvatarUrl(): String {
        return  listOfAvatars[(0..listOfAvatars.size).random()]
    }

    private val listOfAvatars = listOf(
        "https://i.postimg.cc/jLz6d1Kh/105.jpg",
        "https://i.postimg.cc/nX2vqQXj/113.png",
        "https://i.postimg.cc/7Jz3zszw/303.jpg",
        "https://i.postimg.cc/kDRFL4pz/304.jpg",
        "https://i.postimg.cc/NKB6yxjv/306.png",
        "https://i.postimg.cc/62kRBrmS/308.png",
        "https://i.postimg.cc/xkdL5Tms/309.png",
        "https://i.postimg.cc/Tp8qxx8w/311.png",
        "https://i.postimg.cc/svRS8JSG/314.png",
        "https://i.postimg.cc/94NZn5tc/316.jpg",
        "https://i.postimg.cc/JDDZw6PZ/319.jpg",
        "https://i.postimg.cc/Mf1BHckG/321.jpg",
        "https://i.postimg.cc/jCfPZ1Wr/324.jpg",
        "https://i.postimg.cc/nCY7tQYR/325.jpg",
        "https://i.postimg.cc/YvrF2qdp/327.jpg",
        "https://i.postimg.cc/Mc4fY0qT/331.jpg",
        "https://i.postimg.cc/FYTLY560/332.jpg",
        "https://i.postimg.cc/4HvKZrt7/335.png",
        "https://i.postimg.cc/hJh7xZF6/337.jpg",
        "https://i.postimg.cc/KRFkQ8Sn/339.jpg",
        "https://i.postimg.cc/Xp8ZP64t/340.png",
        "https://i.postimg.cc/DJXJpWwc/343.jpg",
        "https://i.postimg.cc/m1WcvbcW/344.jpg",
        "https://i.postimg.cc/jD3Ds6WX/350.png",
        "https://i.postimg.cc/9zywGqSm/352.jpg",
        "https://i.postimg.cc/sMgBdH2x/358.jpg",
        "https://i.postimg.cc/yWhWJCgW/359.png",
        "https://i.postimg.cc/gLpJMN5Y/363.png",
        "https://i.postimg.cc/KKKR4KBy/364.jpg",
        "https://i.postimg.cc/w70vV5kp/367.jpg",
        "https://i.postimg.cc/mc0h9by1/368.jpg",
        "https://i.postimg.cc/tZWJtLBS/369.jpg",
        "https://i.postimg.cc/PvMq01s1/379.jpg",
        "https://i.postimg.cc/LnH5LQLD/380.jpg",
        "https://i.postimg.cc/mzYZF7rK/382.jpg",
        "https://i.postimg.cc/jnvsMKPX/384.png",
        "https://i.postimg.cc/Wdz4fD9J/385.png",
        "https://i.postimg.cc/rRqVGz0s/387.jpg",
        "https://i.postimg.cc/4Y03CJM2/391.png",
        "https://i.postimg.cc/HJ7kdq1Y/392.jpg",
        "https://i.postimg.cc/DJkyZx7q/393.jpg",
        "https://i.postimg.cc/QVZq77mm/394.jpg",
        "https://i.postimg.cc/Z0PxxqDS/403.jpg",
        "https://i.postimg.cc/YhpfnwJm/414.jpg",
        "https://i.postimg.cc/QH0gvy3Y/417.jpg",
        "https://i.postimg.cc/vgF5WRrT/423.jpg",
        "https://i.postimg.cc/68qVKf6W/427.jpg",
        "https://i.postimg.cc/87YRzfsh/434.jpg",
        "https://i.postimg.cc/bG2QDY24/441.jpg",
        "https://i.postimg.cc/BtC21VXZ/447.png",
        "https://i.postimg.cc/MM7V5s5D/448.jpg",
        "https://i.postimg.cc/cr9YFtRV/451.png",
        "https://i.postimg.cc/BPJDVxwG/452.jpg",
        "https://i.postimg.cc/Sj4WRhQt/468.jpg",
        "https://i.postimg.cc/1nQN8GSN/471.jpg",
        "https://i.postimg.cc/jDDfmqvC/474.jpg",
        "https://i.postimg.cc/06mw5tTs/476.jpg",
        "https://i.postimg.cc/MngBSkr5/477.jpg",
        "https://i.postimg.cc/7f6JJPFX/481.png",
        "https://i.postimg.cc/S2fYCnQ1/482.jpg",
        "https://i.postimg.cc/hQ4zrKfD/486.jpg",
        "https://i.postimg.cc/4nX9ZQwD/495.jpg",
        "https://i.postimg.cc/CRnZJPLq/502.jpg",
        "https://i.postimg.cc/qt8NHNKB/514.jpg",
        "https://i.postimg.cc/XG4pjnKf/516.jpg",
        "https://i.postimg.cc/N5z96qYt/519.jpg",
        "https://i.postimg.cc/CZyR2Gz6/522.jpg",
        "https://i.postimg.cc/gXsrr14f/524.jpg",
        "https://i.postimg.cc/K3YjTQH4/527.jpg",
        "https://i.postimg.cc/6T6yF5pw/533.jpg",
        "https://i.postimg.cc/CzzdLP8Q/537.jpg",
        "https://i.postimg.cc/8j9cFRBP/539.jpg",
        "https://i.postimg.cc/BthX39jF/540.jpg",
        "https://i.postimg.cc/FdrHtDmG/541.jpg",
        "https://i.postimg.cc/TKbwnKCj/546.jpg",
        "https://i.postimg.cc/WDP48sHB/547.jpg",
        "https://i.postimg.cc/LqRHfNqR/548.png",
        "https://i.postimg.cc/hz2PKTtn/551.jpg",
        "https://i.postimg.cc/K1tGRd8J/554.png",
        "https://i.postimg.cc/QKVNCCFT/560.jpg",
        "https://i.postimg.cc/QVMd04fd/561.png",
        "https://i.postimg.cc/3WbJ6XqC/563.jpg",
        "https://i.postimg.cc/XpG70ZSM/564.jpg",
        "https://i.postimg.cc/tYKRRXps/565.jpg",
        "https://i.postimg.cc/JyqRQZJX/566.jpg",
        "https://i.postimg.cc/R6Z4phQD/585.png",
        "https://i.postimg.cc/mzpRv4m2/591.jpg",
        "https://i.postimg.cc/VJP1QDWM/597.png",
        "https://i.postimg.cc/kVfdS93F/615.jpg",
        "https://i.postimg.cc/QKBsszzg/618.jpg",
        "https://i.postimg.cc/YhWksJYM/620.jpg",
        "https://i.postimg.cc/QVVjpBMB/628.jpg",
        "https://i.postimg.cc/23JrTzq0/636.jpg",
        "https://i.postimg.cc/vgTwLjGR/638.jpg",
        "https://i.postimg.cc/Mn0wWV9L/651.jpg",
        "https://i.postimg.cc/LYWKyTsJ/654.jpg",
        "https://i.postimg.cc/H87GFyJB/655.jpg",
        "https://i.postimg.cc/mtCfw7Wr/658.jpg",
        "https://i.postimg.cc/Bj3WwtJB/683.jpg",
        "https://i.postimg.cc/SnG00qsF/692.jpg",
        "https://i.postimg.cc/rzdLDvhx/702.jpg",
        "https://i.postimg.cc/4nrRVBMM/710.jpg",
        "https://i.postimg.cc/jnf9MY91/712.png",
        "https://i.postimg.cc/Lq4c27Yq/713.jpg",
        "https://i.postimg.cc/NyNWM4md/714.jpg",
        "https://i.postimg.cc/ykFt9B0g/715.jpg",
        "https://i.postimg.cc/Xr4hkhg2/719.jpg",
        "https://i.postimg.cc/XGptyCg4/723.jpg",
        "https://i.postimg.cc/S233LxZ8/724.jpg",
        "https://i.postimg.cc/YjLJrQ9x/747.jpg",
        "https://i.postimg.cc/4mCjmnHR/756.jpg",
        "https://i.postimg.cc/9Dbk8p8c/771.png",
        "https://i.postimg.cc/B8vVdKkH/772.jpg",
        "https://i.postimg.cc/0rSBRxWW/773.png",
        "https://i.postimg.cc/Jyd2mhCg/775.jpg",
        "https://i.postimg.cc/w7c4M2dD/780.png",
        "https://i.postimg.cc/S2B12tsf/782.png",
        "https://i.postimg.cc/njjwLtHL/783.jpg",
        "https://i.postimg.cc/m17XQN1f/786.jpg",
        "https://i.postimg.cc/f3k8HhSv/791.png",
        "https://i.postimg.cc/Mv8rz4gW/795.jpg",
        "https://i.postimg.cc/KkMJ2rNk/805.png",
        "https://i.postimg.cc/WDKXCfMc/807.jpg",
        "https://i.postimg.cc/yD1QZRVt/827.jpg",
        "https://i.postimg.cc/RWgPQ0hF/831.jpg",
        "https://i.postimg.cc/RqwXpdh2/832.jpg",
        "https://i.postimg.cc/jnYvdwg8/843.jpg",
        "https://i.postimg.cc/v42zxS2x/844.png",
        "https://i.postimg.cc/2qqxQFgc/846.png",
        "https://i.postimg.cc/MvxtCBjH/855.jpg",
        "https://i.postimg.cc/1gLrJVQ9/856.png",
        "https://i.postimg.cc/VJkg6cgc/859.png",
        "https://i.postimg.cc/fk71SDc3/877.png",
        "https://i.postimg.cc/qzjYQwZ4/878.png",
        "https://i.postimg.cc/ygcX5610/879.jpg",
        "https://i.postimg.cc/0KvdqgjF/881.jpg",
        "https://i.postimg.cc/crs2Q2hF/882.jpg",
        "https://i.postimg.cc/nM1wcTxB/885.png",
        "https://i.postimg.cc/YvPPB9HP/889.jpg",
        "https://i.postimg.cc/0MYXS0wg/890.png",
        "https://i.postimg.cc/vDyNskJG/891.png",
        "https://i.postimg.cc/zyvQQh9W/895.jpg",
        "https://i.postimg.cc/hJ75DB4t/897.jpg",
        "https://i.postimg.cc/V0zGW0qJ/898.jpg",
        "https://i.postimg.cc/xcTpkHj7/899.png",
        "https://i.postimg.cc/rdWf6Ngd/905.png",
        "https://i.postimg.cc/phHqP51s/908.png",
        "https://i.postimg.cc/DSSC6TXP/910.jpg",
        "https://i.postimg.cc/N5JJRM50/911.jpg",
        "https://i.postimg.cc/mPKm653f/914.jpg",
        "https://i.postimg.cc/hQ4sdWnx/925.jpg",
        "https://i.postimg.cc/TLJQ12zf/926.png",
        "https://i.postimg.cc/JsHqJ5fT/929.jpg",
        "https://i.postimg.cc/vDG3j9nB/935.jpg",
        "https://i.postimg.cc/HJQ9vssX/966.png",
        "https://i.postimg.cc/1nsckhfT/973.jpg",
        "https://i.postimg.cc/yWnvzdhT/979.jpg",
        "https://i.postimg.cc/KRHfRHHk/988.jpg",
        "https://i.postimg.cc/MM5bxPBG/989.png",
        "https://i.postimg.cc/2qpxxBmC/994.jpg",
        )
}