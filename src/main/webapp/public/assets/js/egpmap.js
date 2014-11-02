/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getTechnicalActivities() {
    $.getJSON("/TAMISLogin/v1/project/egp/technicalactivities", function(data) {
        var fixedMarker;
        var markers = new L.MarkerClusterGroup({spiderfyOnMaxZoom: true, iconCreateFunction: function(cluster) {
                var childrenmarkers = cluster.getAllChildMarkers();
//				var n = 0;
//				for (var i = 0; i < childrenmarkers.length; i++) {
//					n += childrenmarkers[i].number;
//				}
                return L.divIcon({html: childrenmarkers.length, className: 'techcluster', iconSize: L.point(45, 45)});
            }});

        var targetMinis = [];
        var statuses = [];
        for (var dataIndex in data) {
            fixedMarker = addMarker(data[dataIndex].latitude, data[dataIndex].longitude, data[dataIndex]);
            fixedMarker.openPopup();
            markers.addLayer(fixedMarker);
            targetMinis - targetMinis.concat(data[dataIndex].targetMinistries);
            FilterManager.pushMarker({marker: fixedMarker, data: data[dataIndex], show: true, g: "ta"});
            statuses.push(data[dataIndex].status);

        }
        StatusManager.addStatuses(statuses);
        MinistryManager.addMinistries(targetMinis);
        FilterManager.setTAClusterGroup(markers);
        map.addLayer(markers);


    });
}

var MinistryManager = (function setupMinistryManager() {
    var allMinistries = [];
    var callsToAdd = 0;
    return{
        createIfTwo: function() {
            if (callsToAdd === 2) {
                MinistryManager.createDiv();
            }
        },
        getCallsToAdd: function() {
            return callsToAdd;
        },
        addMinistries: function(minis) {
            callsToAdd++;
            for (var i in minis) {
                MinistryManager.addMinistry(minis[i]);
            }
            MinistryManager.createIfTwo();
        },
        addMinistry: function(mini) {
            if (allMinistries.indexOf(mini) === -1) {
                allMinistries.push(mini);
            }
        },
        createDiv: function() {
            var miniFilter = document.getElementById("ministryFilter");

            for (var i in allMinistries) {
                var min = allMinistries[i];
                var li = makeLi();
                $(li).text(min);
                $(li).css("color", '#a0a0a0');
                $(miniFilter).append(li);
                addMinistryClickHandler(li);
            }
            var allLi = makeLi();
            $(allLi).text('all ministries');
            $(miniFilter).append(allLi);
            FilterManager.setSelectedMiniListItem(allLi);
            $(allLi).click(function() {
                FilterManager.clearMiniFilter();
                FilterManager.setSelectedMiniListItem(allLi);
                FilterManager.filterAll();
            });
        }

    };
})();

var StatusManager = (function setupStatusManager() {
    var allStatuses = [];
    var callsToAdd = 0;

    return{
        createIfTwo: function() {
            if (callsToAdd === 2) {
                StatusManager.createDiv();
            }
        },
        getCallsToAdd: function() {
            return callsToAdd;
        },
        addStatuses: function(minis) {
            callsToAdd++;
            for (var i in minis) {
                StatusManager.addStatus(minis[i]);
            }
            StatusManager.createIfTwo();
        },
        addStatus: function(mini) {
            if (allStatuses.indexOf(mini) === -1) {
                allStatuses.push(mini);
            }
        },
        createDiv: function() {
            var statusFilter = document.getElementById("statusFilter");

            for (var i in allStatuses) {
                var li = makeLi();
                var status = allStatuses[i];
                $(li).css("color", '#a0a0a0');

                $(li).text(status);
                $(statusFilter).append(li);
                addStatusClickHandler(li);
            }

            var allLi = makeLi();
            $(allLi).text("all statuses");
            $(statusFilter).append(allLi);
            FilterManager.setSelectedStatusListItem(allLi);
            $(allLi).click(function() {
                FilterManager.clearStatusFilter();
                FilterManager.setSelectedStatusListItem(allLi);
                FilterManager.filterAll();
            });

        }
    };
})();

function makeLi() {
    var li = document.createElement('li');
    $(li).css("cursor", "pointer");
    return li;
}
function addMinistryClickHandler(listItem) {
    $(listItem).click(function() {
        FilterManager.setSelectedMinistry($(this).text());
        FilterManager.filterAll();
        FilterManager.setSelectedMiniListItem(listItem);
    });
}

function addStatusClickHandler(listItem) {

    $(listItem).click(function() {

        FilterManager.setSelectedStatus($(this).text());
        FilterManager.filterAll();
        FilterManager.setSelectedStatusListItem(listItem);
    });

}

var FilterManager = (function setupFilterManager() {
    var allMarkers = [];
    var trainingClusterGroup;
    var taClusterGroup;
    var selectedMinistry = null;
    var selectedStatus = null;
    var selectedMiniListItem = null;
    var selectedStatusListItem = null;
    
    return{
        setSelectedMiniListItem: function(li) {

            if (li !== selectedMiniListItem && selectedMiniListItem !== null) {
                $(selectedMiniListItem).css("color", '#a0a0a0');
            }
            selectedMiniListItem = li;
            $(selectedMiniListItem).css("color", '#fff');

        },
        setSelectedStatusListItem: function(li) {

            if (li !== selectedStatusListItem && selectedStatusListItem !== null) {
                $(selectedStatusListItem).css("color", '#a0a0a0');
            }
            selectedStatusListItem = li;
            $(selectedStatusListItem).css("color", '#fff');

        },
        clearMiniFilter: function() {
            selectedMinistry = null;
        },
        clearStatusFilter: function() {
            selectedStatus = null;
        },
        setSelectedMinistry: function(sm) {
            selectedMinistry = sm;
        },
        setSelectedStatus: function(status) {
            selectedStatus = status;
        },
        setTrainingGroup: function(tg) {
            trainingClusterGroup = tg;
        },
        setTAClusterGroup: function(ta) {
            taClusterGroup = ta;
        },
        pushMarker: function(m) {
            allMarkers.push(m);
        },
        filterAll: function() {
            for (var i in allMarkers) {
                var toHideMarker = !FilterManager.ministryFilter(allMarkers[i]) || !FilterManager.statusFilter(allMarkers[i]);

                // if marker is showing and needs to hide, then hide
                if (toHideMarker && allMarkers[i].show) {
                    if (trainingClusterGroup.hasLayer(allMarkers[i].marker)) {
                        trainingClusterGroup.removeLayer(allMarkers[i].marker);
                    }
                    else if (taClusterGroup.hasLayer(allMarkers[i].marker)) {
                        taClusterGroup.removeLayer(allMarkers[i].marker);

                    }
                    allMarkers[i].show = false;

                }
                else if (!toHideMarker && !allMarkers[i].show) {
                    if (allMarkers[i].g === "tr") {
                        trainingClusterGroup.addLayer(allMarkers[i].marker);

                    } else if (allMarkers[i].g === "ta") {
                        taClusterGroup.addLayer(allMarkers[i].marker);
                    }
                    allMarkers[i].show = true;
                }
            }
        },
        ministryFilter: function(marker) {
            // if filter off return true
            // if filter on and marker has ministry, return true
            if (selectedMinistry === null) {
                return true;
            }

            if (marker.data.targetMinistries.indexOf(selectedMinistry) !== -1) {
                return true;
            }

            return false;
        },
        statusFilter: function(marker) {
            // if filter off return true
            // if filter on and marker has ministry, return true
            if (selectedStatus === null) {
                return true;
            }

            if (marker.data.status === selectedStatus) {

                return true;
            }


            return false;
        }
        


    };
})();

function getTraining() {
    $.getJSON("/TAMISLogin/v1/project/egp/training", function(data) {
        var fixedMarker;
        var markers = new L.MarkerClusterGroup({spiderfyOnMaxZoom: true, iconCreateFunction: function(cluster) {
                var childrenmarkers = cluster.getAllChildMarkers();
//				var n = 0;
//				for (var i = 0; i < childrenmarkers.length; i++) {
//					n += childrenmarkers[i].number;
//				}
                return L.divIcon({html: childrenmarkers.length, className: 'trainingcluster', iconSize: L.point(45, 45)});
//                return L.divIcon({html: "<img src='/TAMISLogin/public/assets/images/greencluster.png' height='30' >"});
            }});
        var minis = [];
        var statuses = [];
        for (var dataIndex in data) {
            fixedMarker = addTrainingMarker(data[dataIndex].latitude, data[dataIndex].longitude, data[dataIndex]);
            fixedMarker.openPopup();
            markers.addLayer(fixedMarker);
            FilterManager.pushMarker({marker: fixedMarker, data: data[dataIndex], show: false, g: "tr"});
            minis = minis.concat(data[dataIndex].targetMinistries);
            statuses.push(data[dataIndex].status);

        }
        StatusManager.addStatuses(statuses);
        MinistryManager.addMinistries(minis);
        map.addLayer(markers);
        FilterManager.setTrainingGroup(markers);
//        FilterManager.hideAll();



    });
}

function addMarker(lat, lon, data) {

    var fixedMarker = L.marker(new L.LatLng(lat, lon), {
        icon: L.mapbox.marker.icon({
            'marker-color': '1C85EB'
        })
//    }).bindPopup(data.name).addTo(map);
    }).bindPopup(data.track + "<br> <b>Status: </b>" + data.status);

    fixedMarker.on('click', function(e) {
        JD.set('title', data.activityHeading);
        JD.set('goal', "<span style='color:#a8a8a8'>Goal: </span>" + data.goal);
        var bi = getStatusImages(data.beforeImages);
        var ai = getStatusImages(data.afterImages);
        var di = getStatusImages(data.duringImages);
        handleHimii(data);

        // make this a function
        var potosblock = $('#photos');
        potosblock.children().remove();
        $('#male').remove();
        $('#female').remove();
        $('#location').remove();
        $('#ps').remove();



        $('.albumtitle').remove();
        showPhotos(bi, 'Before', false);
        showPhotos(di, 'During', true);
        showPhotos(ai, 'After', true);
        add('pc', "<span style='color:#a8a8a8'>Percent Complete: </span>" + data.percentComplete, 'goal');
//        add('ministry', "<span style='color:#a8a8a8'>Ministry: </span>" + data.targetMinistries, 'goal');
        add('location', "<span style='color:#a8a8a8'>Location: </span>" + data.location, 'goal');
        add('enddate', "<span style='color:#a8a8a8'>End Date: </span>" + data.endDate, 'goal');
        add('date', "<span style='color:#a8a8a8'>Start Date: </span>" + data.startDate, 'goal');


    });
    return fixedMarker;
}

function handleHimii(data) {
    if (data.isHimii) {
        $('#himii').show();
    } else {
        $('#himii').hide();
    }
}

function addTrainingMarker(lat, lon, data) {

    var fixedMarker = L.marker(new L.LatLng(lat, lon), {
        icon: L.mapbox.marker.icon({
            'marker-color': '216A12'
        })
//    }).bindPopup(data.name).addTo(map);
    }).bindPopup(data.track + "<br><b>Status: </b>" + data.status);

    fixedMarker.on('click', function(e) {
        JD.set('title', data.activityHeading);
        $('#ministry').remove();
        $('#pc').remove();
        $('#location').remove();
        $('#date').remove();
        handleHimii(data);
        
        document.getElementById('goal').innerHTML = '';
        add('location', "<span style='color:#a8a8a8'>Location: </span>" + data.location, 'goal');
        add('ps', "<span style='color:#a8a8a8'>Percent satisfied: </span>" + data.percentSatisfied, 'goal');

        add('male', "<span style='color:#a8a8a8'>Male attendees: </span>" + data.male, 'goal');
        add('female', "<span style='color:#a8a8a8'>Female attendees: </span>" + data.female, 'goal');
        add('enddate', "<span style='color:#a8a8a8'>End Date: </span>" + data.endDate, 'goal');
        add('date', "<span style='color:#a8a8a8'>Start Date: </span>" + data.startDate, 'goal');

        var images = getStatusImages(data.images);
        // make this a function
        var potosblock = $('#photos');
        potosblock.children().remove();
        $('.albumtitle').remove();
        showPhotos(images, 'Photos', false);

    });
    return fixedMarker;
}

function add(id, data, after) {
    $('#' + id).remove();
    $('#' + after).after("<p style='font-family:Arial; color:#fff; font-size: 10pt;' id = '" + id + "'>" + data + "</p>");
}


function showPhotos(album, title, disabled) {
    var potosblock = $('#photos');
//    potosblock.children().remove();

    if (typeof album !== 'undefined' && album.length > 0) {
        potosblock.show();
        var show = disabled ? 'none' : '';
        var color = disabled ? '#bdbdbd' : '#fff';
        var albumTitle = $("<span class='albumtitle' id ='" + title + "photos' style='padding-left:5px; padding-right:5px;cursor:pointer; font-family:Arial; color:" + color + "'>" + title + "</span>");

        potosblock.before(albumTitle);
        albumTitle.click(function() {
//           $('.'+title).show(); 
//           showHide(title,['Before', 'During', 'After']);
//           $(this).css('color','#fff');
            PhotosSelector.selectPhotos(title);
        });

        for (var photoIndex in album) {
            var aPhoto = $("<span class= '" + title + "' style='display:" + show + "'><img src= /TAMISLogin/" + album[photoIndex] + " width=\"110\"></span>");

            potosblock.append(aPhoto);
            assignPhotoClickHandler(aPhoto, album[photoIndex]);

        }
    }
}

function zoomOut(map) {
    map.setView([31.768319, 35.21370999999999], 10);
}

function assignPhotoClickHandler(photoelement, photosrc) {
    photoelement.click(function() {
        var zoom = $('#zoom');
        $('.zoomphoto').remove();
        zoom.show();

        $('#zoomclose').append("<img class='zoomphoto' src =/TAMISLogin/" + photosrc + ">");
    });

}
function showHide(show, hides) {
    $("." + show).show();


    for (var i in hides) {
        if (hides[i] !== show) {
            $("." + hides[i]).hide();
        }
    }

}

function getStatusImages(images) {
    var imageArray = [];

    if (typeof images !== 'undefined') {
        for (var i in images) {
            imageArray.push(images[i].substring(images[i].indexOf("shrunkenimages")));
        }
    }
    return imageArray;
}

// a closure for setting element values
var JD = (function() {

    return{
        get: function(id) {
            return document.getElementById(id);

        },
        set: function(id, val) {
            this.get(id).innerHTML = val;
        }
    };

})();

var PhotosSelector = (function() {
    var selectedMenu;
    var frames = ['Before', 'During', 'After']

    return {
        selectPhotos: function(timeframe) {

            selectedMenu = $('#' + timeframe + "photos");
            selectedMenu.css('color', '#fff');
            this.hideOtherPhotos(timeframe);


        },
        hideOtherPhotos: function(timeframe) {
            $('.' + timeframe).show();
            for (var i in frames) {
                if (frames[i] !== timeframe) {
                    $('#' + frames[i] + 'photos').css("color", '#bfbfbf');
                    $('.' + frames[i]).hide();
                }

            }
        }

    };
})();


$(document).ready(
        function() {
            getTechnicalActivities();
            getTraining();
            $('#zoomclose').click(function() {
                $('#zoom').hide();
            });
            $('#zoomout').click(function() {
                zoomOut(map);
            });
        }
);

         