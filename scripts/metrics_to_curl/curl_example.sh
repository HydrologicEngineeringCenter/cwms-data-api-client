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
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/location/category/?office=SWT --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/location/group/?office=SWT --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/?office=SWT --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/?office=SWT --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/location/category/ --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/specified-levels/?office=SWT --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/?office=SWT --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/location/group/?includeAssigned=true --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/specified-levels/?office=CWMS --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/location/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/location/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/category/ --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/?office=SWT --header "Accept:application/json" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timeseries/group/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/timeseries/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/ratings/template/?page-size=1000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/ratings/template/?page-size=1000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/ratings/template/?page-size=1000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/?end=2800-01-01T00:00:00Z --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/levels/ &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/ratings/metadata/?page-size=1000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/catalog/locations/?pageSize=5000 --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/states/ --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/timezones/?format=xml --header "Accept:application/xml" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/counties/ --header "Accept:application/json;version=2" &
curl -k --write-out '%{http_code}' --silent --output /dev/null https://leary:8443/swt-data/ratings/metadata/?page-size=1000 --header "Accept:application/json;version=2" &

wait
echo End Time
date +"%r"