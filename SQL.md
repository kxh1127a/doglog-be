                         # 프로시저

##### care tip like
```sql
create function do_like(p_member_id bigint, p_care_tip_id bigint, islike boolean) returns void
    language plpgsql
as
$$
    begin

        if isLike = true THEN

            -- like table
            insert into tip_like (created_at, care_tip_id, member_id) values (NOW(), p_care_tip_id, p_member_id);

            -- member like up
            update member set total_like = total_like + 1
            where id = p_member_id;

            -- care tip like up
            update care_tip set care_tip_like = care_tip_like + 1
            where id = p_care_tip_id;

        ELSE

            -- like table
            delete from tip_like where care_tip_id = p_care_tip_id and member_id = p_member_id;

            -- member like up
            update member set total_like = total_like - 1
            where id = p_member_id;

            -- care tip like up
            update care_tip set care_tip_like = care_tip_like - 1
            where id = p_care_tip_id;

        end if;

    end;
$$;

alter function do_like(bigint, bigint, boolean) owner to postgres;


```


# 뷰테이블

##### 활성화된 케어팁 카테고리 가져오기 (글이 하나라도 있는 것만)
```sql
CREATE OR REPLACE VIEW enabled_caretip_category AS
SELECT
	ct.care_tip_category
FROM care_tip ct
GROUP BY
	ct.care_tip_category;

```