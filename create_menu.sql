 -- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('0', '球员管理', 'player/player', 'player:player:player', '0', 'fa fa-file-code-o', '6');
-- 按钮父菜单ID
set @parentId = @@identity;
-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
		SELECT @parentId, '球员信息', 'player/player', 'player:player:player', '1', 'fa fa-file-code-o', '6';
    
 
