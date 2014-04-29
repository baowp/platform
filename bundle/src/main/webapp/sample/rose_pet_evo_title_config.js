var _pet_title_conf = [{evo:6, title:"荒野小宠"},
    {evo:40, title:"农家小宠"},
    {evo:250, title:"猎户小宠"},
    {evo:1000, title:"破军之宠"},
    {evo:3800, title:"北斗之宠"},
    {evo:9500, title:"仙后之宠"},
    {evo:32000, title:"月影灵宠"},
    {evo:250000, title:"太阳灵宠"},
    {evo:1000000, title:"无极神宠"},
    {evo:2000000, title:"轩辕神宠"}];

var _pet_evo_lvl_conf = [0, 7, 11, 16, 26, 
                        41, 61, 91, 151, 
                        251, 401, 551, 751, 
                        1001, 1501, 2101, 2801, 
                        3801, 4801, 6001, 7501, 
                        9501, 12001, 15001, 20001, 25001, 
                        32001, 42001, 60001, 100001, 150001, 
                        250001, 400001, 500001, 650001, 800001, 
                        1000001, 1200001, 1300001, 1400001, 1500001];
    
function get_pet_title_by_evo(pet_evo)
{
    var tlvl = get_pet_title_lvl(pet_evo);
    return _pet_title_conf[tlvl]['title'];
}

function get_lvl_by_evo(pet_evo)
{
    var level = 0;
    for (var i=0; i<_pet_evo_lvl_conf.length; ++i)
    {
        if (pet_evo < _pet_evo_lvl_conf[i])
        {
            level = i - 1;
            break;
        }
    }
    
    return level<0? 0: level;
}

function get_title_and_level(pet_evo)
{
    return "Lv"+get_lvl_by_evo(pet_evo)+get_pet_title_by_evo(pet_evo);
}

function get_pet_title_lvl(pet_evo)
{
    for (var i=0; i<_pet_title_conf.length; ++i)
    {
        if (pet_evo <= _pet_title_conf[i]['evo'])
        {
            return i;
        }
    }
    
    return _pet_title_conf.length - 1;
}