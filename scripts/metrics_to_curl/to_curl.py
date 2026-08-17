#  MIT License
#
#  Copyright (c) 2023 Hydrologic Engineering Center
#
#  Permission is hereby granted, free of charge, to any person obtaining a copy
#  of this software and associated documentation files (the "Software"), to deal
#  in the Software without restriction, including without limitation the rights
#  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
#  copies of the Software, and to permit persons to whom the Software is
#  furnished to do so, subject to the following conditions:
#
#  The above copyright notice and this permission notice shall be included in all
#  copies or substantial portions of the Software.
#
#  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
#  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
#  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
#  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
#  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
#  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
#  SOFTWARE.

#  MIT License
#
#
#  Permission is hereby granted, free of charge, to any person obtaining a copy
#  of this software and associated documentation files (the "Software"), to deal
#  in the Software without restriction, including without limitation the rights
#  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
#  copies of the Software, and to permit persons to whom the Software is
#  furnished to do so, subject to the following conditions:
#
#
#  MIT License
#
#
#  Permission is hereby granted, free of charge, to any person obtaining a copy
#  of this software and associated documentation files (the "Software"), to deal
#  in the Software without restriction, including without limitation the rights
#  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
#  copies of the Software, and to permit persons to whom the Software is
#  furnished to do so, subject to the following conditions:
#
#
import csv
import re
import sys

regex_accept_header = 'accept:([^\}]*)\}'
regex_parameters = '\{(?!accept:)([^\}]*)\}'


def __export_curl(csv_file, output_file):
    file_content = ''
    with open(csv_file) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter='|')
        line_count = 0
        path_index = 1
        mean_index = 4
        properties_index = 20
        for row in csv_reader:
            if line_count == 0:
                print(f'Column names are {", ".join(row)}')
                path_index = row.index('path')
                properties_index = row.index('Properties')
                mean_index = row.index('mean')
                line_count += 1
            else:
                url = row[path_index].replace('Timer', '')
                result = re.search(regex_parameters, row[properties_index])
                if result:
                    url += '?'
                    for group in result.groups():
                        param_key = group.split(':', 2)[0]
                        url = url + param_key + '=' + group.replace(param_key + ':', '')
                result = re.search(regex_accept_header, row[properties_index])
                accept_header = ''
                if result:
                    accept_header = ' --header "Accept:' + result.groups()[0] + '"'
                file_content += 'curl -k --write-out \'' + url + ' took total time: %{time_total}s while metrics collected averaged: ' \
                                + str(float(row[mean_index]) / 1000) + 's\'' \
                                + ' --silent --output /dev/null ' \
                                + url + accept_header + ' ;echo -e \'\' &\n'
                line_count += 1
        print(file_content)
        with open(output_file, "w") as text_file:
            file_content = 'echo Start Time: \ndate +\"%r\" \necho Running cURL requests\n' + file_content
            file_content += '\nwait\necho End Time\ndate +\"%r\"'
            text_file.write(file_content)


if len(sys.argv) != 3:
    print('Argument List:', str(sys.argv))
    print('Script requires CSV file path and output file')
    print('Example usage \'python to_curl.py timer_example.csv curl_example.sh\'')
else:
    __export_curl(sys.argv[1], sys.argv[2])
