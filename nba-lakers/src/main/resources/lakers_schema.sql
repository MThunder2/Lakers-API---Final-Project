DROP TABLE IF EXISTS Fan_info;
DROP TABLE IF EXISTS Stats;
DROP TABLE IF EXISTS Positions;
DROP TABLE IF EXISTS Fans;
DROP TABLE IF EXISTS Players;

    
CREATE TABLE Players(
    player_id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    years_played INT NOT NULL,
    jersey_number INT NOT NULL,
    PRIMARY KEY (player_id)
);

CREATE TABLE Fans(
    fan_id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    age INT NOT NULL,
    fan_of VARCHAR(100),
    PRIMARY KEY (fan_id)
   
);

CREATE TABLE Positions(
    position_pk INT AUTO_INCREMENT NOT NULL,
    position_id TEXT(50) NOT NULL,
    player_id INT,
    position_name VARCHAR(50),
    PRIMARY KEY (position_pk),
    FOREIGN KEY (player_id) REFERENCES players(player_id) ON DELETE CASCADE
);

CREATE TABLE Stats(
    player_id INT AUTO_INCREMENT NOT NULL,
    position_pk INT NOT NULL,
    points_per_game DECIMAL(5,1) NOT NULL,
    assists_per_game DECIMAL(5,1) NOT NULL,
    rebounds_per_game DECIMAL(5,1) NOT NULL,
    PRIMARY KEY (player_id),
    FOREIGN KEY (position_pk) REFERENCES positions(position_pk) ON DELETE CASCADE,
    FOREIGN KEY (player_id) REFERENCES players(player_id) ON DELETE CASCADE
);

CREATE TABLE Fan_info(
    player_id INT NOT NULL,
    fan_id INT NOT NULL,
    PRIMARY KEY (player_id, fan_id),
    FOREIGN KEY (player_id) REFERENCES players(player_id) ON DELETE CASCADE,
    FOREIGN KEY (fan_id) REFERENCES fans(fan_id) ON DELETE CASCADE
    
);
    