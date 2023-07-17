#
# MIT License
#
# Copyright (c) 2023 Hydrologic Engineering Center
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
#

echo Start Time:
date +"%r" 
echo Running cURL requests
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.44257240000000003s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.37317895s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.348993955s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.334304543s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.321016058s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/location/category/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.07903700000000001s' --silent --output /dev/null https://leary:8443/swt-data/location/category/?office=SWT --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/location/group/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.3493261s' --silent --output /dev/null https://leary:8443/swt-data/location/group/?office=SWT --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/category/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.0386741s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/?office=SWT --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.0430136s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/?office=SWT --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/location/category/ took total time: %{time_total}s while metrics collected averaged: 0.0787993s' --silent --output /dev/null https://leary:8443/swt-data/location/category/ --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/category/ took total time: %{time_total}s while metrics collected averaged: 0.06139065s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/specified-levels/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.1349204s' --silent --output /dev/null https://leary:8443/swt-data/specified-levels/?office=SWT --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/category/ took total time: %{time_total}s while metrics collected averaged: 0.085239967s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/category/ took total time: %{time_total}s while metrics collected averaged: 0.10254795s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.06599129999999999s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/?office=SWT --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/location/group/?includeAssigned=true took total time: %{time_total}s while metrics collected averaged: 0.26275815s' --silent --output /dev/null https://leary:8443/swt-data/location/group/?includeAssigned=true --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.07049325000000001s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.07702621899999999s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/specified-levels/?office=CWMS took total time: %{time_total}s while metrics collected averaged: 0.122101022s' --silent --output /dev/null https://leary:8443/swt-data/specified-levels/?office=CWMS --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.083986827s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.09825440999999999s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/location/group/ took total time: %{time_total}s while metrics collected averaged: 0.25699452700000003s' --silent --output /dev/null https://leary:8443/swt-data/location/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/location/group/ took total time: %{time_total}s while metrics collected averaged: 0.289475661s' --silent --output /dev/null https://leary:8443/swt-data/location/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.116624746s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/category/ took total time: %{time_total}s while metrics collected averaged: 0.13617264499999998s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/?office=SWT took total time: %{time_total}s while metrics collected averaged: 0.173957994s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/?office=SWT --header "Accept:application/json" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.16731736900000002s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.16174282199999998s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.149191888s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.14576603900000001s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timeseries/group/ took total time: %{time_total}s while metrics collected averaged: 0.145782089s' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.37964214500000004s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.37042787099999996s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.364304687s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.35862165199999996s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 0.35163037100000005s' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/ratings/template/?page-size=1000 took total time: %{time_total}s while metrics collected averaged: 2.716796s' --silent --output /dev/null https://leary:8443/swt-data/ratings/template/?page-size=1000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/ratings/template/?page-size=1000 took total time: %{time_total}s while metrics collected averaged: 2.561984312s' --silent --output /dev/null https://leary:8443/swt-data/ratings/template/?page-size=1000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.6418308s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/ratings/template/?page-size=1000 took total time: %{time_total}s while metrics collected averaged: 2.491872081s' --silent --output /dev/null https://leary:8443/swt-data/ratings/template/?page-size=1000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.494126587999999s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.430147975s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.413157232s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.355018695999998s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.403270212999999s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z took total time: %{time_total}s while metrics collected averaged: 9.390072929s' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/levels/ took total time: %{time_total}s while metrics collected averaged: 9.367217258s' --silent --output /dev/null https://leary:8443/swt-data/levels/ ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/ratings/metadata/?page-size=1000 took total time: %{time_total}s while metrics collected averaged: 57.1758383s' --silent --output /dev/null https://leary:8443/swt-data/ratings/metadata/?page-size=1000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/catalog/locations/?pageSize=5000 took total time: %{time_total}s while metrics collected averaged: 94.3179083s' --silent --output /dev/null https://leary:8443/swt-data/catalog/locations/?pageSize=5000 --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/states/ took total time: %{time_total}s while metrics collected averaged: 0.0707465s' --silent --output /dev/null https://leary:8443/swt-data/states/ --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/timezones/?format=xml took total time: %{time_total}s while metrics collected averaged: 0.1549984s' --silent --output /dev/null https://leary:8443/swt-data/timezones/?format=xml --header "Accept:application/xml" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/counties/ took total time: %{time_total}s while metrics collected averaged: 0.121465s' --silent --output /dev/null https://leary:8443/swt-data/counties/ --header "Accept:application/json;version=2" ;echo -e '' &
curl -k --write-out 'https://leary:8443/swt-data/ratings/metadata/?page-size=1000 took total time: %{time_total}s while metrics collected averaged: 53.659005706s' --silent --output /dev/null https://leary:8443/swt-data/ratings/metadata/?page-size=1000 --header "Accept:application/json;version=2" ;echo -e '' &

wait
echo End Time
date +"%r"