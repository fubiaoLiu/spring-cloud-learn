### A、B服务各有一个账户表，A、B服务中账户表的金额可以互转

假设从 `B服务账户(2001)` 向 `A服务账户(1001)` 转账10元
- try: 
  - 服务B：尝试转出，冻结`B服务账户(2001)`10元余额（amount-10，frozen+10）
  - 服务A：尝试转入，`A服务账户(1001)`增加10元冻结金额（frozen+10）
- confirm:
  - 服务B：确认转出，`B服务账户(2001)`10元冻结金额解冻转出（frozen-10）
  - 服务A：确认转入，`A服务账户(1001)`10元冻结金额解冻转入余额（frozen-10，amount+10）
- cancel:
  - 服务B：取消转出，`B服务账户(2001)`10元冻结金额解冻转回余额（frozen-10，amount+10）
  - 服务A：取消转入，`A服务账户(1001)`10元冻结金额作废（frozen-10）