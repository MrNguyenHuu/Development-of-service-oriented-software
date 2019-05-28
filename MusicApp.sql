USE master
CREATE DATABASE MusicApp
GO

USE MusicApp
GO

CREATE TABLE Topic
(
	topic_id BIGINT PRIMARY KEY IDENTITY NOT NULL,
	topic_name NVARCHAR(100),
	topic_image NVARCHAR(MAX)
)

GO

CREATE TABLE Category
(
	category_id BIGINT PRIMARY KEY IDENTITY NOT NULL,
	topic_id NVARCHAR(MAX),
	category_name NVARCHAR(100),
	category_image NVARCHAR(MAX)
)

GO

CREATE TABLE Album
(
	album_id BIGINT PRIMARY KEY IDENTITY NOT NULL,
	album_name NVARCHAR(100),
	album_singer NVARCHAR(100),
	album_image NVARCHAR(MAX)
)

GO

CREATE TABLE Playlist
(
	playlist_id BIGINT PRIMARY KEY IDENTITY NOT NULL,
	playlist_name NVARCHAR(100),
	playlist_background NVARCHAR(MAX),
	playlist_icon NVARCHAR(MAX)
)

GO

CREATE TABLE Song
(
	song_id BIGINT PRIMARY KEY IDENTITY NOT NULL,
	song_name NVARCHAR(100),
	song_image NVARCHAR(MAX),
	song_singer NVARCHAR(100),
	song_url NVARCHAR(MAX),
	album_id NVARCHAR(MAX),
	category_id NVARCHAR(MAX),
	playlist_id NVARCHAR(MAX),
	like_number INT
)

GO

CREATE TABLE Advertisement
(
	advertisement_id BIGINT PRIMARY KEY IDENTITY NOT NULL,
	advertisement_image NVARCHAR(MAX),
	advertisement_content NVARCHAR(MAX),
	song_id BIGINT,

	CONSTRAINT fk_song_id FOREIGN KEY(song_id) REFERENCES dbo.Song(song_id)
	ON DELETE SET NULL
)

GO

/*Store procedure*/
/*advertisement*/
CREATE PROC all_advertisement
AS
BEGIN
	SELECT advertisement_id,advertisement_image,advertisement_content, Song.song_id, 
			song_name,song_image,song_singer,song_url,album_id,category_id,playlist_id,like_number  
	FROM dbo.Advertisement, dbo.Song
	WHERE Advertisement.song_id = Song.song_id
END

/*playlist*/
GO
CREATE PROC getRandom3Playlist
AS
BEGIN
	SELECT TOP 3 * FROM dbo.Playlist ORDER BY NEWID()
END

GO
CREATE PROC getAllPlaylist
AS
BEGIN
	SELECT * FROM dbo.Playlist	
END

/*topic*/
GO
CREATE PROC getRandom4Topic
AS
BEGIN
	SELECT TOP 4 * FROM dbo.Topic ORDER BY NEWID()
END

GO
CREATE PROC getAllTopic
AS
BEGIN
	SELECT * FROM dbo.Topic
END
/*category*/
GO
CREATE PROC getRandom4Category
AS
BEGIN
	SELECT TOP 4 * FROM dbo.Category ORDER BY NEWID()
END

GO
CREATE PROC getAllCategory
AS
BEGIN
	SELECT * FROM dbo.Category
END

GO
CREATE PROC getCategoryFromTopic(@topic_id BIGINT)
AS
BEGIN
	SELECT * FROM dbo.Category
	WHERE topic_id = @topic_id
END
/*album*/
GO

CREATE PROC getRandom4Album
AS
BEGIN
	SELECT TOP 4 * FROM dbo.Album ORDER BY NEWID()
END

GO
CREATE PROC getAllAlbum
AS
BEGIN
	SELECT * FROM dbo.Album
END

/*song*/
GO
CREATE PROC getTop5LikeSong
AS
BEGIN
	SELECT TOP 5 * FROM dbo.Song
	ORDER BY like_number DESC
END

GO
CREATE PROC getSongInPlaylist(@playlist_id NVARCHAR(50))
AS
BEGIN
	SELECT * FROM dbo.Song
	WHERE CONCAT(',',playlist_id,',') LIKE CONCAT('%,',@playlist_id,',%')
END

GO
CREATE PROC getSongInAlbum(@album_id NVARCHAR(50))
AS
BEGIN
	SELECT * FROM dbo.Song
	WHERE CONCAT(',',album_id,',') LIKE CONCAT('%,',@album_id,',%')
END

GO
CREATE PROC getSongInCategory(@category_id NVARCHAR(50))
AS
BEGIN
	SELECT * FROM dbo.Song
	WHERE CONCAT(',',category_id,',') LIKE CONCAT('%,',@category_id,',%')
END

GO
CREATE PROC incLikeNumber(@song_id BIGINT)
AS
BEGIN
	UPDATE dbo.Song SET like_number = like_number + 1
	WHERE song_id = @song_id
END

GO

CREATE PROC findSong(@key NVARCHAR(50))
AS
BEGIN
	SELECT * FROM dbo.Song 
	WHERE LOWER(song_name) LIKE CONCAT('%',@key,'%')
END

GO


/*data*/




INSERT [dbo].[Album] ( [album_name], [album_singer], [album_image]) VALUES ( N'Đúng Người Đúng Thời Điểm (Single)', N'Thanh Hưng', N'album/dungnguoidungthoidiem.jpg')
INSERT [dbo].[Album] ( [album_name], [album_singer], [album_image]) VALUES ( N'Một Bước Yêu Vạn Dặm Đau (Single)', N'Mr Siro', N'song/motbuocyeuvandamdauMrSiro.jpg')
INSERT [dbo].[Album] ( [album_name], [album_singer], [album_image]) VALUES ( N'Hồng Nhan (Single)', N'Jack', N'song/hongnhanJack.jpg')
INSERT [dbo].[Album] ( [album_name], [album_singer], [album_image]) VALUES ( N'MADE', N'BIGBANG', N'album/madeAlbumBigbang.jpg')

INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'1', N'Acoustic EDM', N'category/acousticEDM.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'1', N'K-Pop Acoustics', N'category/kpopacoustic.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'1', N'Acoustic Pop', N'category/acousticPop.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'2', N'Don''t Look Back', N'category/dontLookBack.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'2', N'K-Pop Giai Điệu Buồn', N'category/sadKpop.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'2', N'Đếm Ngày Xa Nhau', N'category/demngayxanhau.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'3', N'EDM Remix', N'category/edmRemix.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'3', N'Nhạc Trẻ Gây Nghiện', N'category/nhactreGaynghien.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'4', N'Nhạc Pop Ballad Việt 2019', N'category/popBalladViet2019.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'4', N'Hot Rap Việt 2019', N'category/hotRapViet.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'5', N'Nhạc Việt Tropical', N'category/vietTropical.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'6', N'K-Pop Morning', N'category/kpopMorning.jpg')
INSERT [dbo].[Category] ( [topic_id], [category_name], [category_image]) VALUES ( N'6', N'Morning Acoustic', N'category/morningAcoustic.jpg')

INSERT [dbo].[Playlist] ( [playlist_name], [playlist_background], [playlist_icon]) VALUES ( N'Top 100 Bài Hát Nhạc Trẻ Hay Nhất', N'playlist/background/top100nhactre.jpg', N'playlist/icon/top100nhactre.jpg')
INSERT [dbo].[Playlist] ( [playlist_name], [playlist_background], [playlist_icon]) VALUES ( N'Top 100 Nhạc Phim Việt Nam Hay Nhất', N'playlist/background/top100nhacphimvietbackground.jpg', N'playlist/icon/top100nhacphimvietnam.jpg')
INSERT [dbo].[Playlist] ( [playlist_name], [playlist_background], [playlist_icon]) VALUES ( N'Top 100 Nhạc Hàn Quốc Hay Nhất', N'playlist/background/top100cakhuchanquocBackground.jpg', N'playlist/icon/top100nhacHan.jpg')
INSERT [dbo].[Playlist] ( [playlist_name], [playlist_background], [playlist_icon]) VALUES ( N'Top 100 Pop Âu Mỹ Hay Nhất', N'playlist/background/top100popUSUKbackground.jpg', N'playlist/icon/top100popaumy.jpg')
INSERT [dbo].[Playlist] ( [playlist_name], [playlist_background], [playlist_icon]) VALUES ( N'Top 100 Nhạc Hoa Hay Nhất', N'playlist/background/top100nhacChina.jpg', N'playlist/icon/top100nhachoa.jpg')

INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Đúng Người Đúng Thời Điểm', N'album/dungnguoidungthoidiem.jpg', N'Thanh Hưng', N'music/Dung-Nguoi-Dung-Thoi-Diem-Thanh-Hung.mp3', N'1', N'8,9', N'1', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Đúng Người Đúng Thời Điểm (Beat)', N'album/dungnguoidungthoidiem.jpg', N'Thanh Hưng', N'music/Dung-Nguoi-Dung-Thoi-Diem-Beat-Thanh-Hung.mp3', N'1', N'0', N'0', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Wonders (Acoustic)', N'song/wonderAcoustic.jpg', N'Klingande, Broken Back', N'music/Wonders-Acoustic-Ultra-Music.mp3', N'0', N'1', N'4', 3)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Hồng Nhan', N'song/hongnhanJack.jpg', N'Jack', N'music/Hong-Nhan-Jack.mp3', N'3', N'8,10', N'1', 1)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Một Bước Yêu Vạn Dặm Đau', N'song/motbuocyeuvandamdauMrSiro.jpg', N'Mr Siro', N'music/Mot-Buoc-Yeu-Van-Dam-Dau-Mr-Siro.mp3', N'2', N'8,9', N'1', 16)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Một Bước Yêu Vạn Dặm Đau (Beat)', N'song/motbuocyeuvandamdauMrSiro.jpg', N'Mr Siro', N'music/Mot-Buoc-Yeu-Van-Dam-Dau-Beat-Mr-Siro.mp3', N'2', N'0', N'0', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Trang Nguyệt Cát', N'song/trangnguyetcatTranee.jpg', N'Tranee, Nguyễn Minh Thắng, K-ICM', N'music/Trang-Nguyet-Cat-Tranee-Nguyen-Minh-Thang-K-ICM.mp3', N'0', N'9', N'0', 1)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Cuộc Vui Cô Đơn', N'song/SongcuocvuicodonLebaobinh.jpg', N'Lê Bảo Bình', N'music/Cuoc-Vui-Co-Don-Le-Bao-Binh.mp3', N'0', N'9', N'0', 0)
INSERT [dbo].[Song] ([song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Suýt Nữa Thì (Chuyến Đi Của Thanh Xuân OST)', N'song/suytnuathiAndiez.jpg', N'Andiez', N'music/Suyt-Nua-Thi-Chuyen-Di-Cua-Thanh-Xuan-OST-Andiez.mp3', N'0', N'9', N'2,1', 5)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Tâm Sự Tuổi 30 (Ông Ngoại Tuổi 30 OST)', N'song/ongngoaituoi30.jpg', N'Trịnh Thăng Bình', N'music/Tam-Su-Tuoi-30-Ong-Ngoai-Tuoi-30-OST-Trinh-Thang-Binh.mp3', N'0', N'9', N'2,1', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Hẹn Một Mai (4 Năm 2 Chàng 1 Tình Yêu OST)', N'song/huamotmaiBuianhtuan.jpg', N'Bùi Anh Tuấn', N'music/Hen-Mot-Mai-4-Nam-2-Chang-1-Tinh-Yeu-OST-Bui-Anh-Tuan.mp3', N'0', N'0', N'2', 3)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Em Sẽ Là Cô Dâu', N'song/emselacodauMinhVuong.jpg', N'Minh Vương M4U, Huy Cung', N'music/Em-Se-La-Co-Dau-Minh-Vuong-M4U-Huy-Cung.mp3', N'0', N'8', N'1', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Way Back Home', N'song/waybackhomeshaun.jpg', N'Shaun', N'music/Way-Back-Home-Shaun.mp3', N'0', N'2,5', N'3', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Fake Love', N'song/fakeloveBTS.jpg', N'BTS', N'music/Fake-Love-BTS.mp3', N'0', N'12', N'3', 8)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Haru Haru', N'song/haruharuBigbang.jpg', N'BIGBANG', N'music/Haru-Haru-Big-Bang.mp3', N'0', N'5', N'3', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'That Girl', N'song/thatgirlOllyMurs.jpg', N'Olly Murs', N'music/That-Girl-Olly-Murs.mp3', N'0', N'6,7', N'4', 1)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Havana', N'song/havanaCamila.jpg', N'Camila Cabello, Young Thug', N'music/Havana-CamilaCabello_YoungThug.mp3', N'0', N'7', N'4', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Until You', N'song/untilYoushayneWard.jpg', N'Shayne Ward', N'music/Until-You-Shayne-Ward.mp3', N'0', N'4', N'4', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Gặp Em Đúng Lúc', N'song/gapemdunglucLyNgocCuong.jpg', N'Lý Ngọc Cương', N'music/GapEmDungLuc-LyNgocCuong.mp3', N'0', N'4', N'5', 13)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Không Chỉ Là Thích', N'song/khongchilathichTonNguTrai.jpg', N'Tôn Ngữ Trại, Tiêu Toàn', N'music/Khong-Chi-La-Thich-Ton-Ngu-Trai_Tieu.mp3', N'0', N'6', N'5', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Học Mèo Kêu', N'song/hocmeokeuPhanphan.jpg', N'Tiểu Phan Phan, Tiểu Phong Phong', N'music/Hoc-Meo-Keu-Tieu-Phan-Phan_Tieu-Phong.mp3', N'0', N'0', N'5', 7)
INSERT [dbo].[Song] ([song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Scared to Be Lonely (Acoustic Version)', N'song/scaretobelonelyMartin.jpg', N'Martin Garrix, Dua Lipa', N'music/Scared-To-Be-Lonely-Acoustic-Version_-.mp3', N'0', N'1', N'4', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Love Me Like You Do', N'song/lovemelikeyoudoEllie.jpg', N'Ellie Goulding', N'music/Love-Me-Like-You-Do-Ellie-Goulding.mp3', N'0', N'6', N'4', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Em Có Thể', N'song/emcotheOSAD.jpg', N'OSAD, VRT', N'music/Em-Co-The-OSAD-VRT.mp3', N'0', N'10,6', N'1', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Bông Hoa Chẳng Thuộc Về Ta', N'song/bonghoachangthuocvetaVietDeus.jpg', N'Việt, Deus', N'music/Bong-Hoa-Chang-Thuoc-Ve-Ta-Viet-Deus.mp3', N'0', N'10', N'1', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'I Know You Know', N'song/iknowuknowSoobin.jpg', N'Soobin Hoàng Sơn', N'music/I-Know-You-Know-Soobin-Hoang-Son.mp3', N'0', N'11,4', N'1', 1)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Tình Yêu Tuyệt Vời (Perfect Love)', N'song/tinhyeutuyetvoiST.jpg', N'S.T 365, NICKY ST.319', N'music/Tinh-Yeu-Tuyet-Voi-Perfect-Love-S-T-365-NICKY-ST-319.mp3', N'0', N'11', N'1', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Bang Bang Bang', N'album/madeAlbumBigbang.jpg', N'BIGBANG', N'music/Bang-Bang-Bang-Big-Bang.mp3', N'4', N'12', N'3', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Loser', N'album/madeAlbumBigbang.jpg', N'BIGBANG', N'music/Loser-Big-Bang.mp3', N'4', N'12,2,5', N'3', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Steal My Girl (Acoustic Version)', N'song/stealmyGirlAcoustic.jpg', N'One Direction', N'music/Steal-My-Girl-Acoustic-Version_-One-Di.mp3', N'0', N'13,3', N'4', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'I Hate U, I Love U', N'song/iloveuihateuGnash.jpg', N'Gnash, Olivia O’brien', N'music/I-Hate-You-I-Love-You-Gnash_OliviaO_.mp3', N'0', N'13,3,4', N'4', 0)
INSERT [dbo].[Song] ( [song_name], [song_image], [song_singer], [song_url], [album_id], [category_id], [playlist_id], [like_number]) VALUES ( N'Reality (Christmas Mix)', N'song/realityChristmas.jpg', N'Lost Frequencies, Janieck Devy', N'music/RealityChristmasMix_-LostFrequencie.mp3', N'0', N'7', N'4', 0)

INSERT [dbo].[Topic] ( [topic_name], [topic_image]) VALUES ( N'Acoustic', N'topic/acoustic.jpg')
INSERT [dbo].[Topic] ( [topic_name], [topic_image]) VALUES ( N'Buồn', N'topic/buonTopic.jpg')
INSERT [dbo].[Topic] ( [topic_name], [topic_image]) VALUES ( N'Hôm Nay Nghe Gì?', N'topic/homnaynghegi.jpg')
INSERT [dbo].[Topic] ( [topic_name], [topic_image]) VALUES ( N'Những Bài Hit', N'topic/nhungbaihit.jpg')
INSERT [dbo].[Topic] ( [topic_name], [topic_image]) VALUES ( N'Thư Giãn', N'topic/thugianTopic.jpg')
INSERT [dbo].[Topic] ( [topic_name], [topic_image]) VALUES ( N'Good Morning', N'topic/goodmorningTopic.jpg')

INSERT [dbo].[Advertisement] ( [advertisement_image], [advertisement_content], [song_id]) VALUES ( N'advertisement/motbuocyeuvandamdauMrSiro.jpg', N'Ngọn cỏ ven đường thôi mà..làm sao với được mây', 5)
INSERT [dbo].[Advertisement] ( [advertisement_image], [advertisement_content], [song_id]) VALUES ( N'advertisement/trangnguyetcatTraneeBackground.jpg', N'Một ca khúc mới ra mắt của ca sĩ  Tranee', 7)
INSERT [dbo].[Advertisement] ( [advertisement_image], [advertisement_content], [song_id]) VALUES ( N'advertisement/cuocvuicodonLebaobinh.jpg', N'Lê Bảo Bình tiếp tục xé nát cõi lòng thính giả', 8)