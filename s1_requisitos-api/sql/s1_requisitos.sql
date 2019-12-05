delete from RequisitosEntity;
delete from servicios;
delete from caminosalternos;
delete from caminosExcepcion;
delete from poscondiciones;
delete from precondiciones;
delete from entidades;
delete from CasoDeUsoEntity ;
delete from desarrolladorEntity;
delete from equipoDesarrolloEntity;
delete from ProyectoEntity;

insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (801, 2075, 'sdosedale0@ovh.net', 193, 'Shela', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (802, 2718, 'amatzeitis1@japanpost.jp', 134, 'Aksel', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (803, 2792, 'bmaris2@washingtonpost.com', 60, 'Bard', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (804, 4781, 'sskyrme3@spiegel.de', 149, 'Samaria', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (805, 2661, 'suebel4@fema.gov', 141, 'Sigismondo', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (806, 3036, 'bbrame5@accuweather.com', 70, 'Bambi', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (807, 2351, 'bfrowen6@shinystat.com', 104, 'Bondie', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (808, 1968, 'jtrippett7@cbsnews.com', 44, 'Janaye', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (809, 3677, 'abottrill8@vk.com', 146, 'Aurelea', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (810, 4487, 'rharrold9@phpbb.com', 178, 'Raychel', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (811, 3423, 'nmaldina@sogou.com', 105, 'Natalee', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (812, 2431, 'klawryb@discovery.com', 147, 'Kyle', 0);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (813, 1485, 'dcamelc@geocities.jp', 100, 'Drona', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (814, 4085, 'cpirried@geocities.com', 191, 'Cull', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (815, 2316, 'bdemangeone@baidu.com', 29, 'Beulah', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (816, 2289, 'dgilchristf@noaa.gov', 158, 'Darell', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (817, 1228, 'fhalfheadg@creativecommons.org', 59, 'Farleigh', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (818, 4133, 'mnannettih@dyndns.org', 182, 'Myrilla', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (819, 4701, 'mwaythingi@state.tx.us', 170, 'Myrtie', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (820, 3403, 'atreneerj@ihg.com', 32, 'Amby', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (821, 3330, 'sstaubynk@ycombinator.com', 47, 'Spence', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (822, 1813, 'cflecknessl@cdbaby.com', 164, 'Cora', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (823, 4865, 'jspellwardm@tinyurl.com', 37, 'Josephina', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (824, 2331, 'rdovinsonn@nature.com', 34, 'Rosanne', 1);
insert into DESARROLLADORENTITY (id, cedula, correo, edad, nombre, tipo) values (825, 1209, 'kwibberleyo@usnews.com', 41, 'Kimmi', 1);



insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (500, 'Himantopus himantopus', 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', 0, 813, 808);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (501, 'Oryx gazella', 'Fusce consequat. Nulla nisl. Nunc nisl.', 0, 813, 805);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (502, 'Coracias caudata', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 0, 817, 807);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (503, 'Ardea golieth', 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', 0, 814, 802);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (504, 'Cercatetus concinnus', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.', 0, 820, 804);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (505, 'Lybius torquatus', 'Fusce consequat. Nulla nisl. Nunc nisl.', 0, 820, 811);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (506, 'Falco peregrinus', 'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 1, 820, 805);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (507, 'Cacatua tenuirostris', 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 1, 814, 807);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (508, 'Eumetopias jubatus', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', 1, 815, 807);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (509, 'Platalea leucordia', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 1, 819, 805);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (510, 'Paradoxurus hermaphroditus', 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', 0, 820, 810);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (511, 'Aonyx cinerea', 'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 1, 822, 812);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (512, 'Macropus robustus', 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', 1, 816, 801);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (513, 'Coluber constrictor foxii', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 0, 822, 812);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (514, 'Fratercula corniculata', 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', 0, 817, 809);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (515, 'Macropus eugenii', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 1, 824, 801);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (516, 'Carduelis pinus', 'Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.', 1, 824, 804);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (517, 'Psittacula krameri', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', 0, 822, 801);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (518, 'Sylvicapra grimma', 'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 0, 815, 805);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (519, 'Boselaphus tragocamelus', 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 0, 824, 812);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (520, 'Tetracerus quadricornis', 'Phasellus in felis. Donec semper sapien a libero. Nam dui.', 1, 814, 801);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (521, 'Naja nivea', 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', 1, 818, 802);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (522, 'Mirounga angustirostris', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', 1, 819, 805);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (523, 'Toxostoma curvirostre', 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 1, 824, 811);
insert into CASODEUSOENTITY (id, nombre, documentacion, pruebas, REPRESENTANTEDELCLIENTE_ID, RESPONSABLE_ID) values (524, 'unavailable', 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 0, 823, 808);


insert into servicios (casodeusoentity_id, servicios) values (500, 'Roe deer');
insert into servicios (casodeusoentity_id, servicios) values (501, 'Bent-toed gecko');
insert into servicios (casodeusoentity_id, servicios) values (502, 'Cat, kaffir');
insert into servicios (casodeusoentity_id, servicios) values (503, 'Mississippi alligator');
insert into servicios (casodeusoentity_id, servicios) values (504, 'Western palm tanager (unidentified)');
insert into servicios (casodeusoentity_id, servicios) values (505, 'Galapagos albatross');
insert into servicios (casodeusoentity_id, servicios) values (506, 'Fox, savanna');
insert into servicios (casodeusoentity_id, servicios) values (507, 'Seal, northern fur');
insert into servicios (casodeusoentity_id, servicios) values (508, 'Common ringtail');
insert into servicios (casodeusoentity_id, servicios) values (509, 'Cormorant, flightless');
insert into servicios (casodeusoentity_id, servicios) values (510, 'Harbor seal');
insert into servicios (casodeusoentity_id, servicios) values (511, 'European shelduck');
insert into servicios (casodeusoentity_id, servicios) values (512, 'Small-spotted genet');
insert into servicios (casodeusoentity_id, servicios) values (513, 'Cockatoo, slender-billed');
insert into servicios (casodeusoentity_id, servicios) values (514, 'Giant girdled lizard');
insert into servicios (casodeusoentity_id, servicios) values (515, 'Common zorro');
insert into servicios (casodeusoentity_id, servicios) values (516, 'African fish eagle');
insert into servicios (casodeusoentity_id, servicios) values (517, 'Cardinal, red-capped');
insert into servicios (casodeusoentity_id, servicios) values (518, 'Cockatoo, red-breasted');
insert into servicios (casodeusoentity_id, servicios) values (519, 'Deer, spotted');
insert into servicios (casodeusoentity_id, servicios) values (520, 'Marmot, hoary');
insert into servicios (casodeusoentity_id, servicios) values (521, 'Butterfly (unidentified)');
insert into servicios (casodeusoentity_id, servicios) values (522, 'Long-crested hawk eagle');
insert into servicios (casodeusoentity_id, servicios) values (523, 'Green-winged trumpeter');
insert into servicios (casodeusoentity_id, servicios) values (524, 'Wood pigeon');

insert into entidades (casodeusoentity_id,entidades) values (500,'Argalis');
insert into entidades (casodeusoentity_id,entidades) values (501,'Sockeye salmon');
insert into entidades (casodeusoentity_id,entidades) values (502,'Red-knobbed coot');
insert into entidades (casodeusoentity_id,entidades) values (503,'Cockatoo, red-tailed');
insert into entidades (casodeusoentity_id,entidades) values (504,'Stanley crane');
insert into entidades (casodeusoentity_id,entidades) values (505,'Sable antelope');
insert into entidades (casodeusoentity_id,entidades) values (506,'Red-necked wallaby');
insert into entidades (casodeusoentity_id,entidades) values (507,'American racer');
insert into entidades (casodeusoentity_id,entidades) values (508,'Duck, comb');
insert into entidades (casodeusoentity_id,entidades) values (509,'Asian elephant');
insert into entidades (casodeusoentity_id,entidades) values (510,'Sea birds (unidentified)');
insert into entidades (casodeusoentity_id,entidades) values (511,'Kangaroo, western grey');
insert into entidades (casodeusoentity_id,entidades) values (512,'Beisa oryx');
insert into entidades (casodeusoentity_id,entidades) values (513,'Toddy cat');
insert into entidades (casodeusoentity_id,entidades) values (514,'Crab, sally lightfoot');
insert into entidades (casodeusoentity_id,entidades) values (515,'Lion, australian sea');
insert into entidades (casodeusoentity_id,entidades) values (516,'Gerenuk');
insert into entidades (casodeusoentity_id,entidades) values (517,'Water moccasin');
insert into entidades (casodeusoentity_identidades) values (518,'Capuchin, brown');
insert into entidades (casodeusoentity_id,entidades) values (519,'Woodpecker, downy');
insert into entidades (casodeusoentity_id,entidades) values (520,'Praying mantis (unidentified)');
insert into entidades (casodeusoentity_id,entidades) values (521,'Spotted-tailed quoll');
insert into entidades (casodeusoentity_id,entidades) values (522,'Western spotted skunk');
insert into entidades (casodeusoentity_id,entidades) values (523,'Eagle, tawny');
insert into entidades (casodeusoentity_id,entidades) values (524,'Blue catfish');

insert into caminosalternos (casodeusoentity_id,caminosalternos) values (500,'Ford');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (501,'Suzuki');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (502,'Ford');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (503,'Mercury');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (504,'Kia');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (505,'Ford');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (506,'Saab');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (507,'Lincoln');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (508,'Mitsubishi');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (509,'Lexus');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (510,'Volkswagen');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (511,'GMC');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (512,'Jaguar');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (513,'Lexus');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (514,'Plymouth');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (515,'Suzuki');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (516,'Ford');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (517,'Volkswagen');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (518,'Honda');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (519,'Audi');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (520,'Acura');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (521,'Buick');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (522,'Mercedes-Benz');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (523,'Toyota');
insert into caminosalternos (casodeusoentity_id,caminosalternos) values (524,'Pontiac');

insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (500,'Sharable motivating success');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (501,'Open-architected bifurcated standardization');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (502,'Devolved tangible hardware');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (503,'De-engineered needs-based workforce');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (504,'User-friendly client-driven matrix');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (505,'Optional discrete customer loyalty');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (506,'Re-engineered fresh-thinking open architecture');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (507,'Self-enabling reciprocal policy');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (508,'Cross-group transitional database');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (509,'Open-architected value-added middleware');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (510,'Focused zero administration matrices');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (511,'Fundamental disintermediate website');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (512,'Quality-focused user-facing superstructure');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (513,'Enhanced tertiary benchmark');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (514,'Enterprise-wide user-facing initiative');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (515,'Total intangible analyzer');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (516,'Vision-oriented real-time architecture');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (517,'Virtual even-keeled synergy');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (518,'Extended zero administration benchmark');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (519,'Organic tertiary standardization');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (520,'Extended transitional hardware');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (521,'Assimilated static capacity');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (522,'User-centric intangible success');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (523,'Organic reciprocal instruction set');
insert into caminosExcepcion (casodeusoentity_id,caminosExcepcion) values (524,'Configurable scalable info-mediaries');

insert into poscondiciones (casodeusoentity_id,poscondiciones) values (500,'Turquoise');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (501,'Maroon');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (502,'Maroon');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (503,'Maroon');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (504,'Violet');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (505,'Indigo');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (506,'Red');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (507,'Green');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (508,'Teal');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (509,'Purple');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (510,'Crimson');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (511,'Puce');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (512,'Turquoise');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (513,'Maroon');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (514,'Mauv');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (515,'Khaki');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (516,'Maroon');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (517,'Yellow');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (518,'Orange');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (519,'Pink');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (520,'Pink');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (521,'Fuscia');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (522,'Fuscia');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (523,'Orange');
insert into poscondiciones (casodeusoentity_id,poscondiciones) values (524,'Turquoise');

insert into precondiciones (casodeusoentity_id,precondiciones) values (500,'Valmiera');
insert into precondiciones (casodeusoentity_id,precondiciones) values (501,'Yuxi');
insert into precondiciones (casodeusoentity_id,precondiciones) values (502,'Nogoonnuur');
insert into precondiciones (casodeusoentity_id,precondiciones) values (503,'Nova Olímpia');
insert into precondiciones (casodeusoentity_id,precondiciones) values (504,'Kuchinarai');
insert into precondiciones (casodeusoentity_id,precondiciones) values (505,'Ningshan Chengguanzhen');
insert into precondiciones (casodeusoentity_id,precondiciones) values (506,'Youyun');
insert into precondiciones (casodeusoentity_id,precondiciones) values (507,'Dolní Bečva');
insert into precondiciones (casodeusoentity_id,precondiciones) values (508,'Jenggawah');
insert into precondiciones (casodeusoentity_id,precondiciones) values (509,'Vaitogi');
insert into precondiciones (casodeusoentity_id,precondiciones) values (510,'Bhopālwāla');
insert into precondiciones (casodeusoentity_id,precondiciones) values (511,'Jing’an');
insert into precondiciones (casodeusoentity_id,precondiciones) values (512,'Rumelange');
insert into precondiciones (casodeusoentity_id,precondiciones) values (513,'Igarapé Miri');
insert into precondiciones (casodeusoentity_id,precondiciones) values (514,'Panikihan');
insert into precondiciones (casodeusoentity_id,precondiciones) values (515,'Aleg');
insert into precondiciones (casodeusoentity_id,precondiciones) values (516,'Shimabara');
insert into precondiciones (casodeusoentity_id,precondiciones) values (517,'Petropavlovsk');
insert into precondiciones (casodeusoentity_id,precondiciones) values (518,'Duayaw Nkwanta');
insert into precondiciones (casodeusoentity_id,precondiciones) values (519,'Hamilton');
insert into precondiciones (casodeusoentity_id,precondiciones) values (520,'Dagang');
insert into precondiciones (casodeusoentity_id,precondiciones) values (521,'Lac La Biche');
insert into precondiciones (casodeusoentity_id,precondiciones) values (522,'Fukumitsu');
insert into precondiciones (casodeusoentity_idprecondiciones) values (523,'Vinha');
insert into precondiciones (casodeusoentity_id,precondiciones) values (524,'Fengshuling');


delete from RequisitosEntity;


insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Mueller, Okuneva and Murphy', 'Frederick Abramov', 'Black-cheeked waxbill', 1, 0, 'Suspendisse potenti. In eleifend quam a odio.', 2, 'Multi-channelled radical toolset');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Hessel, Dooley and Schuppe', 'Rosetta Raubenheim', 'Glider, sugar', 2, 1, 'Curabitur in libero ut massa volutpat convallis.', 2, 'Managed encompassing task-force');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Rowe and Sons', 'Weylin Haysom', 'Macaw, green-winged', 3, 0, 'Praesent id massa id nisl venenatis lacinia.', 3, 'Versatile bifurcated framework');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Robel-Treutel', 'Ray Crosston', 'Russian dragonfly', 4, 1, 'Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 4, 'Triple-buffered disintermediate strategy');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Kozey-Bode', 'Roman Gowland', 'Blackbuck', 5, 0, 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 3, 'Decentralized bandwidth-monitored success');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Mante-Wilderman', 'Todd Boise', 'Bonnet macaque', 6, 1, 'Fusce consequat.', 4, 'Right-sized holistic monitoring');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Cassin-Schmitt', 'Ericka Sandford', 'Giant heron', 7, 1, 'Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 4, 'Configurable stable capacity');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Hirthe-Goyette', 'Kevan Petersen', 'African jacana', 8, 0, 'Praesent lectus.', 4, 'Expanded human-resource capability');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Shields LLC', 'Bonni Christophers', 'Camel, dromedary', 9, 0, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 4, 'Pre-emptive client-server pricing structure');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Shanahan Group', 'Angel Lared', 'Asian openbill', 10, 0, 'Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 3, 'Profound optimal projection');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Crooks-Powlowski', 'Stevena Amery', 'Ring-tailed lemur', 11, 0, 'Morbi a ipsum. Integer a nibh.', 3, 'Secured asymmetric definition');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Volkman-Ward', 'Siward Booker', 'Bleeding heart monkey', 12, 1, 'Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 0, 'Expanded zero defect productivity');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Turner and Sons', 'Adara McKniely', 'Honey badger', 13, 1, 'Morbi non quam nec dui luctus rutrum.', 4, 'Customer-focused leading edge access');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Welch and Sons', 'Wilma Smickle', 'Three-banded plover', 14, 1, 'Duis at velit eu est congue elementum.', 2, 'User-friendly impactful benchmark');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('West, Predovic and Schiller', 'Margot Gregorio', 'Golden brush-tailed possum', 15, 1, 'Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 1, 'Organized bifurcated firmware');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Bahringer and Sons', 'Meade Coulston', 'Asian foreset tortoise', 16, 1, 'Suspendisse potenti.', 3, 'Future-proofed demand-driven architecture');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Durgan, Kertzmann and Cummings', 'Tiffany Antusch', 'Blue catfish', 17, 1, 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', 0, 'Virtual coherent paradigm');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Lesch, Bogan and Fritsch', 'Cam McNiff', 'Bandicoot, long-nosed', 18, 1, 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.', 4, 'Seamless scalable hardware');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Kemmer, Cremin and Haag', 'Fayette Pattullo', 'African elephant', 19, 1, 'Nullam sit amet turpis elementum ligula vehicula consequat.', 3, 'Adaptive fresh-thinking alliance');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('MacGyver-Kozey', 'Jacob MacNalley', 'Wild boar', 20, 1, 'Proin eu mi.', 4, 'User-centric homogeneous alliance');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Bogisich and Sons', 'Trixi Gouly', 'Llama', 21, 0, 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', 0, 'Assimilated secondary methodology');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Bogan-Harvey', 'Margarette Matyushenko', 'Madagascar hawk owl', 22, 1, 'Fusce consequat. Nulla nisl.', 2, 'Seamless zero tolerance service-desk');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Runolfsdottir Group', 'Austin Ruller', 'Sungazer, yellow-brown', 23, 1, 'Integer ac leo. Pellentesque ultrices mattis odio.', 4, 'Optional high-level alliance');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Trantow, Ratke and Luettgen', 'Jillayne Duding', 'Goose, snow', 24, 1, 'Nullam varius.', 4, 'Managed real-time methodology');
insert into RequisitosEntity (fuente, autor, descripcion, importancia, estabilidad, comentariosAdicionales, tipo, nombre) values ('Bashirian and Sons', 'Murry McQuarrie', 'Mynah, common', 25, 1, 'Donec posuere metus vitae ipsum. Aliquam non mauris.', 3, 'Organic maximized toolset');

insert into ProyectoEntity (id, nombre, fechainicial, fechafinal) values (1, 'Aerified', '7/22/2019', '9/22/2020');
insert into ProyectoEntity (id, nombre, fechainicial, fechafinal) values (2, 'Prodder', '9/3/2019', '1/8/2020');
insert into ProyectoEntity (id, nombre, fechainicial, fechafinal) values (3, 'Job', '4/4/2019', '6/11/2020');
insert into ProyectoEntity (id, nombre, fechainicial, fechafinal) values (4, 'Span', '10/30/2019', '8/16/2020');
insert into ProyectoEntity (id, nombre, fechainicial, fechafinal) values (5, 'Tresom', '10/3/2019', '4/16/2020');
insert into ProyectoEntity (id, nombre, fechainicial, fechafinal) values (6, 'Tin', '6/21/2019', '1/28/2020');

insert into equipodesarrolloentity (id, equipodesarrollo) values (500, 'Comuna 13');
insert into equipodesarrolloentity (id, equipodesarrollo) values (501 'Los Jaguares');
insert into equipodesarrolloentity (id, equipodesarrollo) values (502, 'Arepa con huevo');
insert into equipodesarrolloentity (id, equipodesarrollo) values (503, 'RuPauls');
insert into equipodesarrolloentity (id, equipodesarrollo) values (504, 'El equipo');
insert into equipodesarrolloentity (id, equipodesarrollo) values (505, 'Ganadores');
insert into equipodesarrolloentity (id, equipodesarrollo) values (506, 'Delfines');
insert into equipodesarrolloentity (id, equipodesarrollo) values (507, 'Jirafas');
insert into equipodesarrolloentity (id, equipodesarrollo) values (508, 'Mejores que Jirafas');
insert into equipodesarrolloentity (id, equipodesarrollo) values (509, 'Favela');
